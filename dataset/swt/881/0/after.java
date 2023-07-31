class PlaceHold {
  void generateCustomCallbacks() {
    TreeMap set = new TreeMap();
    for (int x = 0; x < xmls.length; x++) {
      Document document = documents[x];
      if (document == null) {
        continue;
      }
      NodeList list = document.getDocumentElement().getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
        Node node = list.item(i);
        if (("class".equals(node.getNodeName()) || "informal_protocol".equals(node.getNodeName()))
            && getGen(node)) {
          NodeList methods = node.getChildNodes();
          for (int j = 0; j < methods.getLength(); j++) {
            Node method = methods.item(j);
            if (("method".equals(method.getNodeName()) && getGen(method))
                && getGenCallback(method)) {
              NamedNodeMap mthAttributes = method.getAttributes();
              String sel = mthAttributes.getNamedItem("selector").getNodeValue();
              set.put(sel, method);
            }
          }
        }
      }
    }
    for (Iterator iterator = set.keySet().iterator(); iterator.hasNext(); ) {
      String key = ((String) (iterator.next()));
      Node method = ((Node) (set.get(key)));
      if ("informal_protocol".equals(method.getParentNode().getNodeName())) {
        method = findNSObjectMethod(method);
        if (method == null) {
          continue;
        }
      }
      String nativeMth = key.replaceAll(":", "_");
      out("/**");
      outln();
      out(" * @method callback_types=");
      Node returnNode = getReturnNode(method.getChildNodes());
      out(returnNode == null ? "void" : getCType(returnNode));
      out(";id;SEL;");
      NodeList params = method.getChildNodes();
      for (int k = 0; k < params.getLength(); k++) {
        Node param = params.item(k);
        if ("arg".equals(param.getNodeName())) {
          out(getCType(param));
          out(";");
        }
      }
      out(",callback_flags=");
      out((returnNode != null) && isStruct(returnNode) ? "struct" : "none");
      out(";none;none;");
      for (int k = 0; k < params.getLength(); k++) {
        Node param = params.item(k);
        if ("arg".equals(param.getNodeName())) {
          out(isStruct(param) ? "struct" : "none");
          out(";");
        }
      }
      outln();
      out(" */");
      outln();
      out("public static final native int /*long*/ CALLBACK_");
      out(nativeMth);
      out("(int /*long*/ func);");
      outln();
    }
  }
}
