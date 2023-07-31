class PlaceHold {
  public void removeAttribute(String attributeName) {
    String key = attributeName.toLowerCase(Locale.ENGLISH);
    attributes.remove(key);
  }
}
