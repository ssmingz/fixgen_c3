class PlaceHold {
  protected void setMapAttribute(Element element) throws CoreException {
    String mapKey = element.getAttribute("key");
    NodeList nodeList = element.getChildNodes();
    int entryCount = nodeList.getLength();
    Map map = new HashMap(entryCount);
    for (int i = 0; i < entryCount; i++) {
      Node node = nodeList.item(i);
      short type = node.getNodeType();
      if (type == Node.ELEMENT_NODE) {
        Element subElement = (Element) node;
        String nodeName = subElement.getNodeName();
        if (!nodeName.equalsIgnoreCase("mapEntry")) {
          throw getInvalidFormatDebugException();
        }
        String key = getKeyAttribute(subElement);
        String value = getValueAttribute(subElement);
        map.put(key, value);
      }
    }
    setAttribute(mapKey, map);
  }
}
