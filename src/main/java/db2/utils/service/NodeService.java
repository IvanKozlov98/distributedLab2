package db2.utils.service;

import generated.org.openstreetmap.osm._0.Node;

import java.util.List;

public interface NodeService {
    Node addNode(Node node);
    void delete(long id);
    Node getByName(String name);
    Node editNode(Node node);
    List<Node> getAll();
}
