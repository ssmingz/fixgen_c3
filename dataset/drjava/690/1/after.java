class PlaceHold {
  public void setActiveNextDocument() {
    INavigatorItem key = getIDocGivenODD(_activeDocument);
    INavigatorItem nextKey = _documentNavigator.getNext(key);
    if (key != nextKey) {
      _documentNavigator.setActiveDoc(nextKey);
    }
  }
}
