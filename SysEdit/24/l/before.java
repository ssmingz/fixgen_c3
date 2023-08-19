class PlaceHold {
  protected void setListAttribute(Element element) throws org.eclipse.core.runtime.CoreException {
    String listKey = element.getAttribute("key"); // $NON-NLS-1$

    NodeList nodeList = element.getChildNodes();
    int entryCount = nodeList.getLength();
    List list = new ArrayList(entryCount);
    for (int i = 0; i < entryCount; i++) {
      Node node = nodeList.item(i);
      short type = node.getNodeType();
      if (type == Node.ELEMENT_NODE) {
        Element subElement = ((Element) (node));
        String nodeName = subElement.getNodeName();
        if (!nodeName.equalsIgnoreCase("listEntry")) {
          // $NON-NLS-1$
          throw getInvalidFormatDebugException();
        }
        String value = getValueAttribute(subElement);
        list.add(value);
      }
    }
    setAttribute(listKey, list);
  }
}
