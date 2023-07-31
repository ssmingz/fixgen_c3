class PlaceHold {
  public void setActivePreviousDocument() {
    INavigatorItem key = _activeDocument;
    OpenDefinitionsDocument prevKey =
        ((OpenDefinitionsDocument) (_documentNavigator.getPrevious(key)));
    if (key != prevKey) {
      setActiveDocument(prevKey);
    }
  }
}
