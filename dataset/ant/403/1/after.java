class PlaceHold {
  public Attribute getAttribute(String attributeName) {
    return ((Attribute) (attributes.get(attributeName.toLowerCase(Locale.ENGLISH))));
  }
}
