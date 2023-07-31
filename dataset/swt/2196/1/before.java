class PlaceHold {
  public void generateProtocolsConst() throws Exception {
    TreeSet set = new TreeSet();
    for (int x = 0; x < xmls.length; x++) {
      Document document = getDocument(xmls[x]);
      NodeList list = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        if ("informal_protocol".equals(node.getNodeName())) {
          NamedNodeMap attributes = node.getAttributes();
          String name = attributes.getNamedItem("name").getNodeValue();
          if (getGenerateClass(name)) {
            set.add(name);
          }
        }
      }
    }
    for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
      String cls = ((String) (iterator.next()));
      String clsConst = "class_" + cls;
      out("public static final int /*long*/ ");
      out(clsConst);
      out(" = ");
      out("objc_getProtocol(\"");
      out(cls);
      out("\");");
      outln();
    }
  }
}
