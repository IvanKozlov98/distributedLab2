package db2.utils.repository;

import generated.org.openstreetmap.osm._0.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Long> {

}
