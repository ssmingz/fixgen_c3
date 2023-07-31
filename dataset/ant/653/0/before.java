class PlaceHold {
  public static Vector getJrePackageTestCases() {
    Vector tests = new Vector();
    tests.addElement("java.lang.Object");
    switch (javaVersionNumber) {
      case 16:
      case 15:
        tests.addElement("com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl ");
      case 14:
        tests.addElement("sun.audio.AudioPlayer");
        if (javaVersionNumber == 14) {
          tests.addElement("org.apache.crimson.parser.ContentModel");
          tests.addElement("org.apache.xalan.processor.ProcessorImport");
          tests.addElement("org.apache.xml.utils.URI");
          tests.addElement("org.apache.xpath.XPathFactory");
        }
        tests.addElement("org.ietf.jgss.Oid");
        tests.addElement("org.w3c.dom.Attr");
        tests.addElement("org.xml.sax.XMLReader");
      case 13:
        tests.addElement("org.omg.CORBA.Any");
        tests.addElement("com.sun.corba.se.internal.corba.AnyImpl");
        tests.addElement("com.sun.jndi.ldap.LdapURL");
        tests.addElement("com.sun.media.sound.Printer");
        tests.addElement("com.sun.naming.internal.VersionHelper");
        tests.addElement("com.sun.org.omg.CORBA.Initializer");
        tests.addElement("sunw.io.Serializable");
        tests.addElement("sunw.util.EventListener");
      case 12:
        tests.addElement("javax.accessibility.Accessible");
        tests.addElement("sun.misc.BASE64Encoder");
        tests.addElement("com.sun.image.codec.jpeg.JPEGCodec");
      case 11:
      default:
        tests.addElement("sun.reflect.SerializationConstructorAccessorImpl");
        tests.addElement("sun.net.www.http.HttpClient");
        tests.addElement("sun.audio.AudioPlayer");
        break;
    }
    return tests;
  }
}
