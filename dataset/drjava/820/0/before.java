class PlaceHold {
  public OpenDefinitionsDocument openFile(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    boolean closeUntitled = _hasOneEmptyDocument();
    OpenDefinitionsDocument oldDoc = _activeDocument;
    OpenDefinitionsDocument openedDoc = super.openFile(com);
    if (closeUntitled) {
      super.closeFile(oldDoc);
    }
    setActiveDocument(openedDoc);
    return openedDoc;
  }
}
