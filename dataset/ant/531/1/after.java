class PlaceHold {
  public Map getNestedElementMap() {
    return nestedTypes.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(nestedTypes);
  }
}
