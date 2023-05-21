package idigitallegacy.root.application.controller;

import idigitallegacy.root.application.entity.BreedsEntity;
import idigitallegacy.root.application.service.property.IPropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prop/breeds")
@Tag(name="Breeds controller", description="Used to add, get or remove breeds.")
public class BreedsController {
    @Autowired
    private IPropertyService<BreedsEntity> breedsService;

    @Operation(
            summary = "Display breeds",
            description = "Displays list of available breeds"
    )
    @GetMapping("/")
    public List<BreedsEntity> breedsList() {
        return breedsService.getAll();
    }

    @Operation(
            summary = "Get breed by id",
            description = "Returns breed related to the provided id"
    )
    @GetMapping("/list/id/{id}")
    public ResponseEntity<BreedsEntity> getById(@PathVariable Integer id) {
        Optional<BreedsEntity> entity = breedsService.getById(id);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get breed by name",
            description = "Returns breed related to the provided name"
    )
    @GetMapping("/list/name/{name}")
    public ResponseEntity<BreedsEntity> getByName(@PathVariable String name) {
        Optional<BreedsEntity> entity = breedsService.getByName(name);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Add breed",
            description = "Adds another breed to the breeds list"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody BreedsEntity entity) {
        breedsService.add(entity);
    }

    @Operation(
            summary = "Remove breed by id",
            description = "Removes breed related to the provided id"
    )
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Integer id) {
        breedsService.removeById(id);
    }

    @Operation(
            summary = "Remove breed by name",
            description = "Removes breed related to the provided name"
    )
    @PostMapping("/remove/{name}")
    public void removeByName(@PathVariable String name) {
        breedsService.removeByName(name);
    }
}