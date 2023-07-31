class PlaceHold {
  public Map getAttributeMap() {
    return attributeTypes.isEmpty()
        ? Collections.EMPTY_MAP
        : Collections.unmodifiableMap(attributeTypes);
  }
}
