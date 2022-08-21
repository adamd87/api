package pl.adamd.crm.api.materials.service.material;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.materials.MaterialMapper;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;

@Service
@AllArgsConstructor
public class MaterialViewServiceImpl implements MaterialViewService {
    private final MaterialService materialService;
    private final MaterialMapper materialMapper;

    @Override
    public List<MaterialDto> getAllMaterials() {
        List<Material> materials = materialService.findAll();
        List<MaterialDto> result = new ArrayList<>();
        for (Material material : materials) {
            MaterialDto materialDto = new MaterialDto();
            materialDto.setId(material.getId());
            materialDto.setName(material.getName());
            materialDto.setProducer(material.getProducer());
            materialDto.setPower(material.getPower());
            materialDto.setCategory(material.getCategory());
            materialDto.setPrice(String.valueOf(material.getPrice()));

            result.add(materialDto);
        }


        return result.stream().sorted(Comparator.comparing(MaterialDto::getName)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public MaterialDto addNewMaterial(MaterialDto materialCreateRequest) {
        Material material = new Material();
        material.setName(materialCreateRequest.getName());
        material.setProducer(materialCreateRequest.getProducer());
        material.setCategory(materialCreateRequest.getCategory());
        material.setPower(material.getPower());
        material.setPrice(new BigDecimal(materialCreateRequest.getPrice()));
        materialService.save(material);
        return materialMapper.mapMaterialToDto(material);
    }

    @Override
    public MaterialDto updateMaterial(Long materialId, MaterialDto updateRequest) {
        Material material = materialService.findById(materialId);

        setIfNotNull(updateRequest.getName(), material::setName);
        setIfNotNull(updateRequest.getProducer(), material::setProducer);
        setIfNotNull(updateRequest.getPower(), material::setPower);
        setIfNotNull(updateRequest.getCategory(), material::setCategory);
        if (updateRequest.getPrice() != null){
            material.setPrice(new BigDecimal(updateRequest.getPrice()));
        }
        materialService.save(material);

        return materialMapper.mapMaterialToDto(material);
    }

    @Override
    public MaterialDto getById(Long id) {
        Material material = materialService.findById(id);
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(material.getId());
        materialDto.setName(material.getName());
        materialDto.setProducer(material.getProducer());
        materialDto.setPower(material.getPower());
        materialDto.setCategory(material.getCategory());
        materialDto.setPrice(String.valueOf(material.getPrice()));

        return materialDto;
    }
}
