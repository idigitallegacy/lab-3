package idigitallegacy.root.application.controller;

import idigitallegacy.root.application.entity.FriendCatsEntity;
import idigitallegacy.root.application.entity.OwnersEntity;
import idigitallegacy.root.application.entity.key.FriendCatsEntityPK;
import idigitallegacy.root.application.service.entity.IEntityService;
import idigitallegacy.root.application.service.relation.IFriendsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relations/cats/friends")
@Tag(name="Cats relationship controller", description="Used to add, get or remove friendship between two cats.")
public class FriendCatsController {
    @Autowired
    private IFriendsService friendsService;

    @Operation(
            summary = "Display relations",
            description = "Displays list of all friends"
    )
    @GetMapping("/")
    public List<FriendCatsEntity> ownersList() {
        return friendsService.getAll();
    }

    @Operation(
            summary = "Get friendship by id",
            description = "Returns all friends found by given id"
    )
    @GetMapping("/list/id/{id}")
    public List<FriendCatsEntity> getById(@PathVariable Integer id) {
        return friendsService.getById(id);
    }

    @Operation(
            summary = "Add a relation",
            description = "Adds a relation to the relations list"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody FriendCatsEntity entity) {
        friendsService.add(entity);
    }

    @Operation(
            summary = "Remove relations by id",
            description = "Removes all friendships related to the provided id"
    )
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Integer id) {
        friendsService.removeById(id);
    }

    @Operation(
            summary = "Remove relation",
            description = "Removes specified friendship"
    )
    @PostMapping("/remove/{entity}")
    public void removeByName(@PathVariable FriendCatsEntity entity) {
        friendsService.remove(entity);
    }
}
