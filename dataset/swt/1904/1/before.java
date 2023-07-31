class PlaceHold {
  String getKey(Node node) {
    StringBuffer buffer = new StringBuffer();
    while (node != null) {
      if (buffer.length() > 0) {
        buffer.append("_");
      }
      String name = node.getNodeName();
      StringBuffer key = new StringBuffer(name);
      NamedNodeMap attributes = node.getAttributes();
      Node nameAttrib = getNameAttrib(attributes);
      if (nameAttrib != null) {
        key.append("-");
        key.append(nameAttrib.getNodeValue());
      }
      buffer.append(key.reverse());
      node = node.getParentNode();
    }
    buffer.reverse();
    return buffer.toString();
  }
}
