class PlaceHold {
  public void addEnclosingData(Data enclosingData) {
    assert enclosingData != null;
    if (!_enclosingData.contains(enclosingData)) {
      _enclosingData.addFirst(enclosingData);
    }
  }
}
