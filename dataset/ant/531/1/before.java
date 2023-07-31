class PlaceHold {
  public Map getNestedElementMap() {
    return nestedTypes.size() < 1 ? EMPTY_MAP : Collections.unmodifiableMap(nestedTypes);
  }
}
