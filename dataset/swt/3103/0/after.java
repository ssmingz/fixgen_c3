class PlaceHold {
  public void generateClassesConst() throws Exception {
    HashSet set = new HashSet();
    for (int x = 0; x < xml.length; x++) {
      Document document = getDocument(xml[x]);
      NodeList list = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        if ("class".equals(node.getNodeName())) {
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
      out("public static final int ");
      out(clsConst);
      out(" = ");
      out("objc_getClass(\"");
      out(cls);
      out("\");");
      outln();
    }
  }
}
