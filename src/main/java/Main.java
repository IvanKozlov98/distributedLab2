import db.DataBaseInitializer;
import db.MainDatabase;
import db2.utils.service.NodeService;
import db2.utils.service.impl.NodeServiceImpl;
import generated.org.openstreetmap.osm._0.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 1 lab
        /*
        List<Node> nodes = new ArrayList<>();
        // fill nodes
        for (int i = 0; i < 10000; ++i) {
            nodes.add(new Node());
        }

        new MainDatabase().run(nodes);
        */

        NodeService nodeService = new NodeServiceImpl();
        for (int i = 0; i < 10000; ++i) {
            nodeService.addNode(new Node());
        }

    }
}
