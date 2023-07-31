class PlaceHold {
  private Object createDynamicElement(Object parent, String ns, String localName, String qName) {
    Object nestedElement = null;
    if (parent instanceof DynamicElementNS) {
      DynamicElementNS dc = ((DynamicElementNS) (parent));
      nestedElement = dc.createDynamicElement(ns, localName, qName);
    }
    if ((nestedElement == null) && (parent instanceof DynamicElement)) {
      DynamicElement dc = ((DynamicElement) (parent));
      nestedElement = dc.createDynamicElement(localName.toLowerCase(Locale.ENGLISH));
    }
    return nestedElement;
  }
}
