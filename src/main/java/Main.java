import db.DataBaseInitializer;
import db.MainDatabase;
import generated.org.openstreetmap.osm._0.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //List<Node> nodes = new ArrayList<>();
        // fill nodes
        //for (int i = 0; i < 100000; ++i) {
        //    nodes.add(new Node());
        //}

        new MainDatabase().run(new TLab().run());
    }
}
