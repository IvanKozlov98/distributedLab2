package db2.utils.service;

import db2.utils.Model.Node;

import java.util.List;
import java.util.Optional;

public interface NodeService {
    Node addNode(Node node);
    boolean delete(long id);
    //Node getByName(String name);
    List<Node> getNearNodes(double x, double y, double r);
    Node editNode(Node node);
    boolean editById(Node node, long id);
    List<Node> getAll();
    Optional<Node> getById(long id);
}
