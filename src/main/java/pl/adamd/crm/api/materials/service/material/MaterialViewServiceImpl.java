package pl.adamd.crm.api.materials.service.material;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.materials.MaterialMapper;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;

import java.math.BigDecimal;
import java.util.List;

import static pl.adamd.crm.api.common.Utils.setIfNotNull;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialViewServiceImpl implements MaterialViewService {
    private final MaterialService materialService;
    private final MaterialMapper materialMapper;

    @Override
    public List<Material> getAllMaterials() {


        return materialService.findAll();
    }

    @Override
    public ResponseEntity<Material> addNewMaterial(MaterialDto materialCreateRequest) {

        ResponseEntity<Material> responseMaterial;
        try {
            Material material = new Material();
            if (materialCreateRequest.getId() != null) {
                material = materialService.findById(materialCreateRequest.getId());
            }
            if (materialCreateRequest.getName()
                                     .isEmpty()) {
                throw new NullPointerException();
            }
            material.setName(materialCreateRequest.getName());
            material.setProducer(materialCreateRequest.getProducer());
            material.setCategory(materialCreateRequest.getCategory());
            material.setPower(materialCreateRequest.getPower());
            material.setPrice(new BigDecimal(materialCreateRequest.getPrice()));
            materialService.save(material);
            responseMaterial = new ResponseEntity<>(material, HttpStatus.CREATED);
        } catch (NumberFormatException exception) {
            responseMaterial = ResponseEntity.status(HttpStatus.FORBIDDEN)
                                             .build();
            log.error(exception.getMessage() + " Number format error! Price: '" + materialCreateRequest.getPrice() +
                              "' should be number! " + responseMaterial.getStatusCode());
        } catch (NullPointerException exception) {
            responseMaterial = ResponseEntity.status(HttpStatus.FORBIDDEN)
                                             .build();
            log.error(exception.getMessage() + " value! 'Name' is empty " + responseMaterial.getStatusCode());
        }
        return responseMaterial;

    }

    @Override
    public Material updateMaterial(Long materialId, MaterialDto updateRequest) {
        Material material = materialService.findById(materialId);

        setIfNotNull(updateRequest.getName(), material::setName);
        setIfNotNull(updateRequest.getProducer(), material::setProducer);
        setIfNotNull(updateRequest.getPower(), material::setPower);
        setIfNotNull(updateRequest.getCategory(), material::setCategory);
        if (updateRequest.getPrice() != null) {
            material.setPrice(new BigDecimal(updateRequest.getPrice()));
        }
        materialService.save(material);

        return material;
    }

    @Override
    public Material getById(Long id) {
        Material material = materialService.findById(id);
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(material.getId());
        materialDto.setName(material.getName());
        materialDto.setProducer(material.getProducer());
        materialDto.setPower(material.getPower());
        materialDto.setCategory(material.getCategory());
        materialDto.setPrice(String.valueOf(material.getPrice()));

        return material;
    }
}
