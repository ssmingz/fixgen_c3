class PlaceHold {
  public String getAttributeValue(String attributeName) {
    Attribute attribute = getAttribute(attributeName.toLowerCase());
    if (attribute == null) {
      return null;
    }
    return attribute.getValue();
  }
}
