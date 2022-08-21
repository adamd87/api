package pl.adamd.crm.api.materials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.adamd.crm.api.materials.entity.Material;


@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
