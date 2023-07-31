class PlaceHold {
  public void generateEnums() throws Exception {
    for (int j = 0; j < xml.length; j++) {
      Document document = getDocument(xml[j]);
      NodeList list = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        if ("enum".equals(node.getLocalName())) {
          NamedNodeMap attributes = node.getAttributes();
          Node value = attributes.getNamedItem("value");
          if (value != null) {
            out("public static final ");
            if (value.getNodeValue().indexOf('.') != (-1)) {
              out("double ");
            } else {
              out("int ");
            }
            out(attributes.getNamedItem("name").getNodeValue());
            out(" = ");
            out(value.getNodeValue());
            out(";");
            outln();
          }
        }
      }
    }
  }
}
