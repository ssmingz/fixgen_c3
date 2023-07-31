class PlaceHold {
  public List<String> getMultiple(String path) {
    if (isDelegated()) {
      return getMultiple(path, _startNode);
    }
    return getMultiple(path, _document);
  }
}
