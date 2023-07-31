class PlaceHold {
  public void setActiveNextDocument() {
    INavigatorItem key = _activeDocument;
    OpenDefinitionsDocument nextKey = ((OpenDefinitionsDocument) (_documentNavigator.getNext(key)));
    if (key != nextKey) {
      setActiveDocument(nextKey);
    }
  }
}
