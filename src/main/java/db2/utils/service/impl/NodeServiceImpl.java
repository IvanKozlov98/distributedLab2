package db2.utils.service.impl;

import db2.utils.repository.NodeRepository;
import db2.utils.service.NodeService;
import generated.org.openstreetmap.osm._0.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public Node addNode(Node node) {
        return nodeRepository.saveAndFlush(node);
    }

    @Override
    public void delete(long id) {
        nodeRepository.delete(id);
    }

    /*@Override
    public Node getByName(String name) {
        return nodeRepository.findByName(name);
    }*/

    @Override
    public Node editNode(Node node) {
        return nodeRepository.saveAndFlush(node);
    }

    @Override
    public List<Node> getAll() {
        return nodeRepository.findAll();
    }
}
