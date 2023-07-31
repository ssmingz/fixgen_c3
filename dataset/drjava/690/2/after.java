class PlaceHold {
  public boolean closeFile(OpenDefinitionsDocument doc) {
    INavigatorItem switchTo = _documentNavigator.getNext(getIDocGivenODD(doc));
    if (switchTo == getIDocGivenODD(doc)) {
      switchTo = _documentNavigator.getPrevious(switchTo);
    }
    if (super.closeFile(doc)) {
      if (!_isClosingAllDocs) {
        _ensureNotEmpty();
        if (getDefinitionsDocumentsSize() == 1) {
          _setActiveFirstDocument();
        } else {
          _documentNavigator.setActiveDoc(switchTo);
        }
      }
      return true;
    }
    return false;
  }
}
