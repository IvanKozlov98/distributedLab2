package db2.utils.repository;

import generated.org.openstreetmap.osm._0.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
