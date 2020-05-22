package db2.utils.repository;

import generated.org.openstreetmap.osm._0.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NodeRepository extends JpaRepository<Node, Long> {

    //@Query("select n from nodes n where n.nameOfUser = :name")
    //Node findByName(@Param("name") String name);
}
