class PlaceHold {
  public Map getAttributeMap() {
    return attributeTypes.size() < 1 ? EMPTY_MAP : Collections.unmodifiableMap(attributeTypes);
  }
}
