package db2.utils.repository;

import generated.org.openstreetmap.osm._0.Way;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WayRepository extends JpaRepository<Way, Long> {
}
