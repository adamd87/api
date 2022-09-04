package pl.adamd.crm.api.materials.service.material;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.adamd.crm.api.materials.entity.Material;
import pl.adamd.crm.api.materials.repository.MaterialRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialServiceImpl
        implements MaterialService {
    private final MaterialRepository materialRepository;

    @Override
    public Material findById(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new RuntimeException("The specified material does not exist");
        }
        return materialRepository.findById(id)
                                 .get();
    }

    @Override
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }


}
