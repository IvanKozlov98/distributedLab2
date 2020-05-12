import generated.org.openstreetmap.osm._0.Node;
import generated.org.openstreetmap.osm._0.Osm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class TLab {
    //Slf4J
    final static Logger logger = LoggerFactory.getLogger(TLab.class);
    private Map<BigInteger, Map.Entry<String, BigInteger>> userToChangeset = new HashMap<>();
    private Map<BigInteger, Long> keyToCount = new HashMap<>();

    private void printSortedUserToChangeset() {
        logger.info("start sort entries");
        userToChangeset.entrySet().stream()
                .sorted((o1, o2) -> {
                    BigInteger changesetFirst = o1.getValue().getValue();
                    BigInteger changesetSecond = o2.getValue().getValue();
                    return changesetFirst.compareTo(changesetSecond);
                })
                .forEach(entry -> System.out.println(entry.getValue().getKey() + " : " + entry.getValue().getValue()));
        logger.info("end sorting");
    }

    private void printKeyToCount() {
        keyToCount.entrySet().stream().forEach(System.out::println);
    }


    private void lab1(Osm osm) {
        List<Node> listOfNodes = osm.getNode();
        for (Node node : listOfNodes) {
            String userName = node.getUser();
            BigInteger key = node.getUid();
            BigInteger changeset = node.getChangeset();
            userToChangeset.put(key, new AbstractMap.SimpleEntry<>(userName, changeset));
            //
            Long countKey = keyToCount.getOrDefault(key, 0L);
            keyToCount.put(key, countKey + 1);
        }
    }

    public void run() {

        try (InputStream in = new BufferedInputStream(new FileInputStream("RU-NVS.osm"))) {
            logger.info("start treat xml file");
            JAXBContext context = JAXBContext.newInstance(Osm.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Osm osm = (Osm) unmarshaller.unmarshal(in);
            lab1(osm);
        } catch (IOException e) {
            logger.warn("ioexception ", e);
        } catch (JAXBException ee) {
            logger.warn("xmll", ee);
        } finally {
            logger.info("end treat xml file");
            printSortedUserToChangeset();
            System.out.println("//////////////////");
            printKeyToCount();
        }
        logger.info("end application");
    }
}
