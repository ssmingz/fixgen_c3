class PlaceHold {
  public static Vector getJrePackageTestCases() {
    Vector tests = new Vector();
    tests.addElement("java.lang.Object");
    switch (javaVersionNumber) {
      case VERSION_1_6:
      case VERSION_1_5:
        tests.addElement("com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl ");
      case VERSION_1_4:
        tests.addElement("sun.audio.AudioPlayer");
        if (javaVersionNumber == VERSION_1_4) {
          tests.addElement("org.apache.crimson.parser.ContentModel");
          tests.addElement("org.apache.xalan.processor.ProcessorImport");
          tests.addElement("org.apache.xml.utils.URI");
          tests.addElement("org.apache.xpath.XPathFactory");
        }
        tests.addElement("org.ietf.jgss.Oid");
        tests.addElement("org.w3c.dom.Attr");
        tests.addElement("org.xml.sax.XMLReader");
      case VERSION_1_3:
        tests.addElement("org.omg.CORBA.Any");
        tests.addElement("com.sun.corba.se.internal.corba.AnyImpl");
        tests.addElement("com.sun.jndi.ldap.LdapURL");
        tests.addElement("com.sun.media.sound.Printer");
        tests.addElement("com.sun.naming.internal.VersionHelper");
        tests.addElement("com.sun.org.omg.CORBA.Initializer");
        tests.addElement("sunw.io.Serializable");
        tests.addElement("sunw.util.EventListener");
      case VERSION_1_2:
        tests.addElement("javax.accessibility.Accessible");
        tests.addElement("sun.misc.BASE64Encoder");
        tests.addElement("com.sun.image.codec.jpeg.JPEGCodec");
      case VERSION_1_1:
      default:
        tests.addElement("sun.reflect.SerializationConstructorAccessorImpl");
        tests.addElement("sun.net.www.http.HttpClient");
        tests.addElement("sun.audio.AudioPlayer");
        break;
    }
    return tests;
  }
}
