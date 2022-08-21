package pl.adamd.crm.api.materials.service.material;



import pl.adamd.crm.api.materials.entity.Material;

import java.util.List;

public interface MaterialService {
    Material findById(Long id);

    List<Material> findAll();

    Material save(Material material);


}
