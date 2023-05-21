package idigitallegacy.root.application.controller;

import idigitallegacy.root.application.entity.ColorsEntity;
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
@RequestMapping("/prop/colors")
@Tag(name="Colors controller", description="Used to add, get or remove colors.")
public class ColorsController {
    @Autowired
    private IPropertyService<ColorsEntity> colorsService;

    @Operation(
            summary = "Display colors",
            description = "Displays list of available colors"
    )
    @GetMapping("/")
    public List<ColorsEntity> colorsList() {
        return colorsService.getAll();
    }

    @Operation(
            summary = "Get color by id",
            description = "Returns color related to the provided id"
    )
    @GetMapping("/list/id/{id}")
    public ResponseEntity<ColorsEntity> getById(@PathVariable Integer id) {
        Optional<ColorsEntity> entity = colorsService.getById(id);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Get color by name",
            description = "Returns color related to the provided name"
    )
    @GetMapping("/list/name/{name}")
    public ResponseEntity<ColorsEntity> getByName(@PathVariable String name) {
        Optional<ColorsEntity> entity = colorsService.getByName(name);
        if (entity.isPresent())
            return ResponseEntity.ok(entity.get());
        return ResponseEntity.badRequest().body(null);
    }

    @Operation(
            summary = "Add color",
            description = "Adds another color to the colors list"
    )
    @PostMapping("/add")
    public void add(@Valid @RequestBody ColorsEntity entity) {
        colorsService.add(entity);
    }

    @Operation(
            summary = "Remove color by id",
            description = "Removes color related to the provided id"
    )
    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Integer id) {
        colorsService.removeById(id);
    }

    @Operation(
            summary = "Remove color by name",
            description = "Removes color related to the provided name"
    )
    @PostMapping("/remove/{name}")
    public void removeByName(@PathVariable String name) {
        colorsService.removeByName(name);
    }
}