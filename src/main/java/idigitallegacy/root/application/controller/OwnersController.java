package idigitallegacy.root.application.controller;


import idigitallegacy.root.application.entity.OwnersEntity;
import idigitallegacy.root.application.service.entity.IEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entity/owners")
@Tag(name="Owners controller", description="Used to add, get or remove owners.")
public class OwnersController {
    @Autowired
    private IEntityService<OwnersEntity> ownersService;

    @Operation(
            summary = "Display owners",
            description = "Displays list of available owners"
    )
    @GetMapping("/")
    public List<OwnersEntity> ownersList() {
        return ownersService.getAll();
    }

    @Operation(
            summary = "Get owner by id",
            description = "Returns owner related to the provided id"
    )
    @GetMapping("/list/id/{id}")
    public ResponseEntity<OwnersEntity> getById(@PathVariable Integer id) {
        Optional<OwnersEntity> entity = ownersService.getById(id);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get owner by name",
            description = "Returns owner related to the provided name"
    )
    @GetMapping("/list/name/{name}")
    public ResponseEntity<OwnersEntity> getByName(@PathVariable String name) {
        Optional<OwnersEntity> entity = ownersService.getByName(name);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get all owners by its owner",
            description = "Returns owner related to the provided owner id"
    )
    @GetMapping("/list/owner_id/{relatedId}")
    public ResponseEntity<List<OwnersEntity>> getByOwnerId(@PathVariable Integer relatedId) {
        List<OwnersEntity> entities = ownersService.getAllByRelatedEntityId(relatedId);
        return ResponseEntity.ok(entities);
    }

    @Operation(
            summary = "Add owner",
            description = "Adds another owner to the owners list"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody OwnersEntity entity) {
        ownersService.add(entity);
    }

    @Operation(
            summary = "Remove owner by id",
            description = "Removes owner related to the provided id"
    )
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Integer id) {
        ownersService.removeById(id);
    }

    @Operation(
            summary = "Remove owner by name",
            description = "Removes owner related to the provided name"
    )
    @PostMapping("/remove/{name}")
    public void removeByName(@PathVariable String name) {
        ownersService.removeByName(name);
    }

    @Operation(
            summary = "Remove owner by its cat",
            description = "Removes owner related to the provided cat id"
    )
    @PostMapping("/remove/cat_id/{relatedId}")
    public void removeByCatId(@PathVariable Integer relatedId) {
        ownersService.removeByRelatedEntityId(relatedId);
    }
}
