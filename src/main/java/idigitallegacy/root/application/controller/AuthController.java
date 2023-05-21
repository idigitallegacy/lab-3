package idigitallegacy.root.application.controller;

import idigitallegacy.root.application.config.JwtUtils;
import idigitallegacy.root.application.dto.AuthDto;
import idigitallegacy.root.application.dto.AuthenticatedUserResponseDto;
import idigitallegacy.root.application.dto.RefreshTokenRequestDto;
import idigitallegacy.root.application.dto.RefreshTokenResponseDto;
import idigitallegacy.root.application.entity.*;
import idigitallegacy.root.application.exceptions.NotAuthorizedException;
import idigitallegacy.root.application.mapper.UserMapper;
import idigitallegacy.root.application.service.auth.AuthService;
import idigitallegacy.root.application.service.auth.UserDetailsService;
import idigitallegacy.root.application.service.auth.RefreshTokenService;
import idigitallegacy.root.application.service.auth.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsServiceImpl;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthService authService, JwtUtils jwtUtils, RefreshTokenService refreshTokenService, UserService userService, UserDetailsService userDetailsServiceImpl) {
        this.authService = authService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Operation(summary = "Registration")
    @PostMapping("/reg")
    public ResponseEntity<AuthenticatedUserResponseDto> registration(@Valid @RequestBody AuthDto registrationDto) {
        UserEntity user = this.authService.registration(registrationDto);
        user.setActive(true);
        user.getRoles().add(RoleEntity.ROLE_USER);
        this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(user);
        String jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return ResponseEntity.ok(new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user)));
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserResponseDto> login(@Valid @RequestBody AuthDto loginDto) {
        UserEntity user = this.authService.login(loginDto);
        this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
        String jwtAccessToken = jwtUtils.generateJwtAccessToken(user);
        TokenEntity tokenEntity = refreshTokenService.findByUserId(user.getId());
        String jwtRefreshToken;
        if (tokenEntity != null) {
            refreshTokenService.deleteByUserId(user.getId());
        }
        jwtRefreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return ResponseEntity.ok(new AuthenticatedUserResponseDto(jwtAccessToken, jwtRefreshToken, UserMapper.INSTANCE.toDTO(user)));
    }

    @Operation(summary = "Refresh authentication")
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshAuth(@Valid @RequestBody RefreshTokenRequestDto request) {
        String requestRefreshToken = request.getRefreshToken();
        TokenEntity tokenEntity = refreshTokenService.findByToken(requestRefreshToken);
        if (tokenEntity != null) {
            refreshTokenService.verifyExpiration(tokenEntity);
            UserEntity user = userService.findById(tokenEntity.getUserId());
            if (user != null) {
                this.userDetailsServiceImpl.AuthenticateUserInContext(user.getEmail());
                String accessToken = jwtUtils.generateJwtAccessToken(user);
                return ResponseEntity.ok(new RefreshTokenResponseDto(accessToken, requestRefreshToken));
            }
        }
        throw new BadCredentialsException("Invalid refresh token");
    }

    @Operation(security = {@SecurityRequirement(name = "bearer-key")}, summary = "Logout path")
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        try {
            UserEntity userDetails = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
            return ResponseEntity.ok("Logged out");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new NotAuthorizedException();
        }
    }
}