class PlaceHold {
  public void setActivePreviousDocument() {
    INavigatorItem key = getIDocGivenODD(_activeDocument);
    INavigatorItem prevKey = _documentNavigator.getPrevious(key);
    if (key != prevKey) {
      _documentNavigator.setActiveDoc(prevKey);
    }
  }
}
