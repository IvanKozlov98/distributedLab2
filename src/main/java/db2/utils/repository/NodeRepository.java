package db2.utils.repository;

import db2.utils.Model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<Node, BigInteger> {

    //@Query("select n from nodes n where n.nameOfUser = :name")
    //Node findByName(@Param("name") String name);
 /*   @Query("select node from (\n" +
            "select *, earth_distance(ll_to_earth(lat, lon), ll_to_earth(:x, :y)) as earth_distance from nodes\n" +
            ") as nodes_with_distance where node.earth_distance < :r Order by node.earth_distance;")
    public List<Node> getNearNodes(@Param("x")double x, @Param("y") double y,@Param("r") double r);

*/
    /**
     * with nodes_with_distance as (
     * 	select *, earth_distance(ll_to_earth(lat, lon), ll_to_earth(1.1, 1.1)) as earth_distance from nodes
     * )
     * select * from nodes_with_distance where earth_distance < 3700000.0 Order by earth_distance;
     * */
/**
 *     @Query(
 *             value = "select nn from (\n" +
 *             "select *, earth_distance(ll_to_earth(lat, lon), ll_to_earth(:x, :y)) as earth_distance from nodes\n" +
 *             ") as nodes_with_distance nn where nn.earth_distance < :r Order by nn.earth_distance;",
 *             nativeQuery = true
 *     )
 * */


    @Query(value = "with nodes_with_distance as (\n" +
            "\tselect *, earth_distance(ll_to_earth(lat, lon), ll_to_earth(:x, :y)) as earth_distance from nodes\n" +
            ")\t\t\t\t\t\t \n" +
            "select * from nodes_with_distance where earth_distance < :r Order by earth_distance;",
            nativeQuery = true
    )
    public List<Node> getNearNodes(@Param("x")double x, @Param("y") double y, @Param("r") double r);


}
