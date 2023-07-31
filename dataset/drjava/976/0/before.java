class PlaceHold {
  public void removeRegions(OpenDefinitionsDocument doc) {
    assert doc != null;
    boolean found = _documents.remove(doc);
    if (found) {
      _regions.remove(doc);
    }
  }
}
