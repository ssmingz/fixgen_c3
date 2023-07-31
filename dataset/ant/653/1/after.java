class PlaceHold {
  private static void buildJrePackages() {
    jrePackages = new Vector();
    switch (javaVersionNumber) {
      case VERSION_1_6:
      case VERSION_1_5:
        jrePackages.addElement("com.sun.org.apache");
      case VERSION_1_4:
        if (javaVersionNumber == VERSION_1_4) {
          jrePackages.addElement("org.apache.crimson");
          jrePackages.addElement("org.apache.xalan");
          jrePackages.addElement("org.apache.xml");
          jrePackages.addElement("org.apache.xpath");
        }
        jrePackages.addElement("org.ietf.jgss");
        jrePackages.addElement("org.w3c.dom");
        jrePackages.addElement("org.xml.sax");
      case VERSION_1_3:
        jrePackages.addElement("org.omg");
        jrePackages.addElement("com.sun.corba");
        jrePackages.addElement("com.sun.jndi");
        jrePackages.addElement("com.sun.media");
        jrePackages.addElement("com.sun.naming");
        jrePackages.addElement("com.sun.org.omg");
        jrePackages.addElement("com.sun.rmi");
        jrePackages.addElement("sunw.io");
        jrePackages.addElement("sunw.util");
      case VERSION_1_2:
        jrePackages.addElement("com.sun.java");
        jrePackages.addElement("com.sun.image");
      case VERSION_1_1:
      default:
        jrePackages.addElement("sun");
        jrePackages.addElement("java");
        jrePackages.addElement("javax");
        break;
    }
  }
}
