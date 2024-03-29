package idigitallegacy.root.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticatedUserResponseDto {
    private String accessToken;
    private String refreshToken;
    private final String type = "Bearer";
    private UserDto user;
}