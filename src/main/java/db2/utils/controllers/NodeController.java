package db2.utils.controllers;

import db2.utils.service.NodeService;
import db2.utils.Model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;


    @RequestMapping(value = "/nodes", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Node node) {
        nodeService.addNode(node);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public ResponseEntity<List<Node>> read() {
        final List<Node> nodes = nodeService.getAll();
        return null != nodes && !nodes.isEmpty()
                ? new ResponseEntity<>(nodes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Node> read(@PathVariable(value = "id") long id) {
        final Optional<Node> node = nodeService.getById(id);
        return node.isPresent()
                ? new ResponseEntity<>(node.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(value = "id") long id, @RequestBody Node node) {
        boolean updated = nodeService.editById(node, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
        final boolean deleted = nodeService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/nodes",
            params = {"x", "y", "r"},
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Node>> readNearNodes(@RequestParam(value = "x") double x,
                                                    @RequestParam(value = "y") double y,
                                                    @RequestParam(value = "r") double r) {
        final List<Node> nearNodes = nodeService.getNearNodes(x, y, r);
        return null != nearNodes && !nearNodes.isEmpty()
                ? new ResponseEntity<>(nearNodes, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
