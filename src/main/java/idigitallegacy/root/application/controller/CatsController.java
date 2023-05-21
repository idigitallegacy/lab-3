package idigitallegacy.root.application.controller;


import idigitallegacy.root.application.entity.CatsEntity;
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
@RequestMapping("/entity/cats")
@Tag(name="Cats controller", description="Used to add, get or remove cats.")
public class CatsController {
    @Autowired
    private IEntityService<CatsEntity> catsService;

    @Operation(
            summary = "Display cats",
            description = "Displays list of available cats"
    )
    @GetMapping("/")
    public List<CatsEntity> catsList() {
        return catsService.getAll();
    }

    @Operation(
            summary = "Get cat by id",
            description = "Returns cat related to the provided id"
    )
    @GetMapping("/list/id/{id}")
    public ResponseEntity<CatsEntity> getById(@PathVariable Integer id) {
        Optional<CatsEntity> entity = catsService.getById(id);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get cat by name",
            description = "Returns cat related to the provided name"
    )
    @GetMapping("/list/name/{name}")
    public ResponseEntity<CatsEntity> getByName(@PathVariable String name) {
        Optional<CatsEntity> entity = catsService.getByName(name);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get all cats by its owner",
            description = "Returns cat related to the provided owner id"
    )
    @GetMapping("/list/owner_id/{relatedId}")
    public ResponseEntity<List<CatsEntity>> getByOwnerId(@PathVariable Integer relatedId) {
        List<CatsEntity> entities = catsService.getAllByRelatedEntityId(relatedId);
        return ResponseEntity.ok(entities);
    }

    @Operation(
            summary = "Add cat",
            description = "Adds another cat to the cats list"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody CatsEntity entity) {
        catsService.add(entity);
    }

    @Operation(
            summary = "Remove cat by id",
            description = "Removes cat related to the provided id"
    )
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Integer id) {
        catsService.removeById(id);
    }

    @Operation(
            summary = "Remove cat by name",
            description = "Removes cat related to the provided name"
    )
    @PostMapping("/remove/{name}")
    public void removeByName(@PathVariable String name) {
        catsService.removeByName(name);
    }

    @Operation(
            summary = "Remove cat by its ownerId",
            description = "Removes all cats related to the provided owner id"
    )
    @PostMapping("/remove/owner_id/{relatedId}")
    public void removeByOwnerId(@PathVariable Integer relatedId) {
        catsService.removeByRelatedEntityId(relatedId);
    }
}
