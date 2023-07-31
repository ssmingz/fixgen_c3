class PlaceHold {
  public Map getNestedElementMap() {
    if (nestedTypes.size() < 1) {
      return EMPTY_MAP;
    }
    return Collections.unmodifiableMap(nestedTypes);
  }
}
