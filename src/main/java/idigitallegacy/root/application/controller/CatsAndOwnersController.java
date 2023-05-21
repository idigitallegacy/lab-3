package idigitallegacy.root.application.controller;


import idigitallegacy.root.application.entity.CatsAndOwnersEntity;
import idigitallegacy.root.application.entity.CatsEntity;
import idigitallegacy.root.application.service.entity.IEntityService;
import idigitallegacy.root.application.service.relation.ICatsAndOwnersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relations/cats_and_owners/")
@Tag(name="Cats and owners controller", description="Used to add, get or remove cats.")
public class CatsAndOwnersController {
    @Autowired
    private ICatsAndOwnersService coreService;

    @Operation(
            summary = "Display all",
            description = "Displays all info about cats and their owners"
    )
    @GetMapping("/")
    public List<CatsAndOwnersEntity> list() {
        return coreService.getAll();
    }

    @Operation(
            summary = "Get info by cat id",
            description = "Returns record related to the provided cat id"
    )
    @GetMapping("/list/id/cat/{id}")
    public List<CatsAndOwnersEntity> getByCatId(@PathVariable Integer id) {
        return coreService.getByCatId(id);
    }

    @Operation(
            summary = "Get info by owner id",
            description = "Returns record related to the provided owner id"
    )
    @GetMapping("/list/id/owner/{id}")
    public List<CatsAndOwnersEntity> getByOwnerId(@PathVariable Integer id) {
        return coreService.getByOwnerId(id);
    }

    @Operation(
            summary = "Get info by cat name",
            description = "Returns record related to the provided cat name"
    )
    @GetMapping("/list/name/cat/{name}")
    public List<CatsAndOwnersEntity> getByCatName(@PathVariable String name) {
        return coreService.getByCatName(name);
    }

    @Operation(
            summary = "Get info by owner name",
            description = "Returns record related to the provided owner name"
    )
    @GetMapping("/list/name/owner/{name}")
    public List<CatsAndOwnersEntity> getByOwnerName(@PathVariable String name) {
        return coreService.getByOwnerName(name);
    }

    @Operation(
            summary = "Add record"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody CatsAndOwnersEntity entity) {
        coreService.add(entity);
    }

    @Operation(
            summary = "Remove record by cat id",
            description = "Removes record related to the provided cat id"
    )
    @GetMapping("/remove/id/cat/{id}")
    public void removeByCatId(@PathVariable Integer id) {
        coreService.removeByCatId(id);
    }

    @Operation(
            summary = "Remove record by owner id",
            description = "Removes record related to the provided owner id"
    )
    @GetMapping("/remove/id/owner/{id}")
    public void removeByOwnerId(@PathVariable Integer id) {
        coreService.removeByOwnerId(id);
    }

    @Operation(
            summary = "Remove record by cat name",
            description = "Removes record related to the provided cat name"
    )
    @GetMapping("/remove/name/cat/{name}")
    public void removeByCatName(@PathVariable String name) {
        coreService.removeByCatName(name);
    }

    @Operation(
            summary = "Remove record by owner name",
            description = "Removes record related to the provided owner name"
    )
    @GetMapping("/remove/name/owner/{name}")
    public void removeByOwnerName(@PathVariable String name) {
        coreService.removeByOwnerName(name);
    }

    @Operation(
            summary = "Remove record",
            description = "Removes specified record"
    )
    @GetMapping("/remove/record/{entity}")
    public void removeByCatName(@PathVariable CatsAndOwnersEntity entity) {
        coreService.remove(entity);
    }
}
