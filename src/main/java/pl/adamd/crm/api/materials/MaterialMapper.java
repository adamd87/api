package pl.adamd.crm.api.materials;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pl.adamd.crm.api.materials.dto.MaterialDto;
import pl.adamd.crm.api.materials.entity.Material;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MaterialMapper {

    List<Material> mapDtoListToMaterial(List<MaterialDto> materialViewResponse);

    List<MaterialDto> mapMaterialsListToDto(List<Material> materials);

    Material mapDtoToMaterial(MaterialDto materialViewResponse);

    MaterialDto mapMaterialToDto(Material material);


}
