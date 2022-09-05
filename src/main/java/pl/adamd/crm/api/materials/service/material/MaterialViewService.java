package pl.adamd.crm.api.materials.service.material;

import org.springframework.http.ResponseEntity;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;

import java.util.List;

public interface MaterialViewService {
    List<Material> getAllMaterials();

    ResponseEntity<Material> addNewMaterial(MaterialDto materialCreateRequest);

    Material updateMaterial(Long materialId, MaterialDto materialCreateRequest);

    Material getById(Long id);

//    MaterialViewResponse increaseMaterialCount(Long materialId, MaterialIncreaseCountRequest request);
}
