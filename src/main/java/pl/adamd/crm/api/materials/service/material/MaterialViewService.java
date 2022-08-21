package pl.adamd.crm.api.materials.service.material;

import pl.adamd.crm.api.materials.dto.MaterialDto;

import java.util.List;

public interface MaterialViewService {
    List<MaterialDto> getAllMaterials();

    MaterialDto addNewMaterial(MaterialDto materialCreateRequest);

    MaterialDto updateMaterial(Long materialId, MaterialDto materialCreateRequest);

    MaterialDto getById(Long id);

//    MaterialViewResponse increaseMaterialCount(Long materialId, MaterialIncreaseCountRequest request);
}
