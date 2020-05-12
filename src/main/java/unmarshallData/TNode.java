package unmarshallData;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * <xs:element name="node">
 * 		<xs:complexType>
 * 			<xs:sequence>
 * 				<xs:element ref="tag" minOccurs="0" maxOccurs="unbounded"/>
 * 			</xs:sequence>
 * 			<xs:attribute name="id" type="xs:unsignedLong"/>
 * 			<xs:attribute name="lat" type="xs:double"/>
 * 			<xs:attribute name="lon" type="xs:double"/>
 * 			<xs:attribute name="user" type="xs:string"/>
 * 			<xs:attribute name="uid" type="xs:unsignedLong"/>
 * 			<xs:attribute name="visible" type="xs:boolean"/>
 * 			<xs:attribute name="version" type="xs:unsignedLong"/>
 * 			<xs:attribute name="changeset" type="xs:unsignedLong"/>
 * 			<xs:attribute name="timestamp" type="xs:dateTime"/>
 * 		</xs:complexType>
 * 	</xs:element>
 * */

@XmlType(name = "node")
public class TNode {
    //List<TTag>
    //@XmlAttribute

    @XmlAttribute(name = "id")
    public long id;
    @XmlAttribute(name = "lat")
    public long lat;
    @XmlAttribute(name = "lon")
    public long lon;
    @XmlAttribute(name = "user")
    public long user;
    @XmlAttribute(name = "uid")
    public long uid;
    @XmlAttribute(name = "visible")
    public long visible;
    @XmlAttribute(name = "changeset")
    public long changeset;
    @XmlAttribute(name = "timestamp")
    public long timestamp;

    public TNode()
    {
    }
}
