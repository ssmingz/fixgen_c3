class PlaceHold {
  protected void setMapAttribute(Element element) throws org.eclipse.core.runtime.CoreException {
    String mapKey = element.getAttribute(KEY);
    NodeList nodeList = element.getChildNodes();
    int entryCount = nodeList.getLength();
    Map map = new HashMap(entryCount);
    Node node = null;
    Element selement = null;
    for (int i = 0; i < entryCount; i++) {
      node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        selement = ((Element) (node));
        if (!selement.getNodeName().equalsIgnoreCase(MAP_ENTRY)) {
          throw getInvalidFormatDebugException();
        }
        map.put(getKeyAttribute(selement), getValueAttribute(selement));
      }
    }
    setAttribute(mapKey, map);
  }
}
