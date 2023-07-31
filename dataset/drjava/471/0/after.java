class PlaceHold {
  public List<Node> getNodes(String path) {
    if (isDelegated()) {
      return getNodes(path, _startNode);
    }
    return getNodes(path, _document);
  }
}
