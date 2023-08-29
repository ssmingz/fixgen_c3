class PlaceHold {
  protected void setListAttribute(Element element) throws CoreException {
    String listKey = element.getAttribute("key");
    NodeList nodeList = element.getChildNodes();
    int entryCount = nodeList.getLength();
    List list = new ArrayList(entryCount);
    for (int i = 0; i < entryCount; i++) {
      Node node = nodeList.item(i);
      short type = node.getNodeType();
      if (type == Node.ELEMENT_NODE) {
        Element subElement = (Element) node;
        String nodeName = subElement.getNodeName();
        if (!nodeName.equalsIgnoreCase("listEntry")) {
          throw getInvalidFormatDebugException();
        }
        String value = getValueAttribute(subElement);
        list.add(value);
      }
    }
    setAttribute(listKey, list);
  }
}
