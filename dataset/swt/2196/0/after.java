class PlaceHold {
  void generateClassesConst() {
    TreeSet set = new TreeSet();
    for (int x = 0; x < xmls.length; x++) {
      Document document = documents[x];
      if (document == null) {
        continue;
      }
      Hashtable extras = extraAttributes[x];
      NodeList list = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        if ("class".equals(node.getNodeName())) {
          NamedNodeMap attributes = node.getAttributes();
          String name = attributes.getNamedItem("name").getNodeValue();
          set.add(name);
        }
      }
    }
    for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
      String cls = ((String) (iterator.next()));
      String clsConst = "class_" + cls;
      out("public static final int /*long*/ ");
      out(clsConst);
      out(" = ");
      out("objc_getClass(\"");
      out(cls);
      out("\");");
      outln();
    }
  }
}
