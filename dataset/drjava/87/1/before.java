class PlaceHold {
  public void addEnclosingData(Data enclosingData) {
    if (!_enclosingData.contains(enclosingData)) {
      _enclosingData.addFirst(enclosingData);
    }
  }
}
