package pl.adamd.crm.api.materials.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;
import pl.adamd.crm.api.materials.service.material.MaterialViewService;

import java.util.List;

@Controller
@RequestMapping("/api/auth/material")
@AllArgsConstructor
public class MaterialController {

    private final MaterialViewService materialViewService;


    @GetMapping("/all")
    ResponseEntity<List<Material>> getAll() {
        return ResponseEntity.ok(materialViewService.getAllMaterials());
    }

    @GetMapping("/allList")
    public ResponseEntity<List<Material>> getAllList() {

        return ResponseEntity.ok(materialViewService.getAllMaterials());

    }

    @GetMapping("/by-id/{id}")
    ResponseEntity<Material> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materialViewService.getById(id));
    }

    @PutMapping("/add")
    public ResponseEntity<Material> createNewMaterial(@RequestBody MaterialDto material) {
        return materialViewService.addNewMaterial(material);
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<Material> updateById(
            @PathVariable Long id, @RequestBody MaterialDto request) {
        return ResponseEntity.ok(materialViewService.updateMaterial(id, request));
    }
}
