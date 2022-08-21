package pl.adamd.crm.api.materials.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.service.material.MaterialViewService;

import java.util.List;

@Controller
@RequestMapping("/api/auth/material")
@AllArgsConstructor
public class MaterialController {

    private final MaterialViewService materialViewService;


    @GetMapping("/all")
    ResponseEntity<List<MaterialDto>> getAll() {
        return ResponseEntity.ok(materialViewService.getAllMaterials());
    }

    @GetMapping("/allList")
    public ResponseEntity<List<MaterialDto>> getAllList() {

        return ResponseEntity.ok(materialViewService.getAllMaterials());

    }

    @GetMapping("/by-id/{id}")
    ResponseEntity<MaterialDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(materialViewService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<MaterialDto> createNewMaterial(@RequestBody MaterialDto material) {
        return ResponseEntity.ok(materialViewService.addNewMaterial(material));
    }

    @PatchMapping("/update/{id}")
    ResponseEntity<MaterialDto> updateById(@PathVariable Long id,
                                           @RequestBody MaterialDto request) {
        return ResponseEntity.ok(materialViewService.updateMaterial(id, request));
    }
}
