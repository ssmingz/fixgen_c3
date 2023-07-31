class PlaceHold {
  public Map getAttributeMap() {
    if (attributeTypes.size() < 1) {
      return EMPTY_MAP;
    }
    return Collections.unmodifiableMap(attributeTypes);
  }
}
