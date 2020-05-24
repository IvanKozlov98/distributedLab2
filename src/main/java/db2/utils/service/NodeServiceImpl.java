package db2.utils.service;

import db2.utils.repository.NodeRepository;
import db2.utils.Model.Node;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public Node addNode(Node node) {
        return nodeRepository.saveAndFlush(node);
    }

    @Override
    public boolean delete(long id) {
        nodeRepository.deleteById(BigInteger.valueOf(id));
        return true;
    }

    @Override
    public List<Node> getNearNodes(double x, double y, double r) {
        return nodeRepository.getNearNodes(x, y, r);
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
    public boolean editById(Node node, long id) {
        Optional<Node> nodeInDb = getById(id);
        if (!nodeInDb.isPresent())
            return false;
        // copy
        nodeInDb.get().setUser(node.getUser());
        nodeInDb.get().setLat(node.getLat());
        nodeInDb.get().setLon(node.getLon());
        nodeInDb.get().setId(node.getId());
        nodeInDb.get().setChangeset(node.getChangeset());
        nodeInDb.get().setUid(node.getUid());
        nodeInDb.get().setVersion(node.getVersion());
        nodeRepository.save(nodeInDb.get());
        return true;
    }

    @Override
    public List<Node> getAll() {
        return nodeRepository.findAll();
    }

    @Override
    public Optional<Node> getById(long id) {
        return nodeRepository.findById(BigInteger.valueOf(id));
    }
}
