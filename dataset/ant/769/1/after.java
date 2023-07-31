class PlaceHold {
  public String getAttributeValue(String attributeName) {
    Attribute attribute = getAttribute(attributeName.toLowerCase(Locale.ENGLISH));
    if (attribute == null) {
      return null;
    }
    return attribute.getValue();
  }
}
