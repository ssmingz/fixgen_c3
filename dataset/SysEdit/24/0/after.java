class PlaceHold {
  protected void setListAttribute(Element element) throws CoreException {
    String listKey = element.getAttribute(KEY);
    NodeList nodeList = element.getChildNodes();
    int entryCount = nodeList.getLength();
    List list = new ArrayList(entryCount);
    Node node = null;
    Element selement = null;
    for (int i = 0; i < entryCount; i++) {
      node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        selement = (Element) node;
        if (!selement.getNodeName().equalsIgnoreCase(LIST_ENTRY)) {
          throw getInvalidFormatDebugException();
        }
        list.add(getValueAttribute(selement));
      }
    }
    setAttribute(listKey, list);
  }
}
