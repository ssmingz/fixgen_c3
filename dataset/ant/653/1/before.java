class PlaceHold {
  private static void buildJrePackages() {
    jrePackages = new Vector();
    switch (javaVersionNumber) {
      case 16:
      case 15:
        jrePackages.addElement("com.sun.org.apache");
      case 14:
        if (javaVersionNumber == 14) {
          jrePackages.addElement("org.apache.crimson");
          jrePackages.addElement("org.apache.xalan");
          jrePackages.addElement("org.apache.xml");
          jrePackages.addElement("org.apache.xpath");
        }
        jrePackages.addElement("org.ietf.jgss");
        jrePackages.addElement("org.w3c.dom");
        jrePackages.addElement("org.xml.sax");
      case 13:
        jrePackages.addElement("org.omg");
        jrePackages.addElement("com.sun.corba");
        jrePackages.addElement("com.sun.jndi");
        jrePackages.addElement("com.sun.media");
        jrePackages.addElement("com.sun.naming");
        jrePackages.addElement("com.sun.org.omg");
        jrePackages.addElement("com.sun.rmi");
        jrePackages.addElement("sunw.io");
        jrePackages.addElement("sunw.util");
      case 12:
        jrePackages.addElement("com.sun.java");
        jrePackages.addElement("com.sun.image");
      case 11:
      default:
        jrePackages.addElement("sun");
        jrePackages.addElement("java");
        jrePackages.addElement("javax");
        break;
    }
  }
}
