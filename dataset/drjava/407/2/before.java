class PlaceHold {
  public OpenDefinitionsDocument openFile(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    boolean closeUntitled = _hasOneEmptyDocument();
    OpenDefinitionsDocument oldDoc = _activeDocument;
    OpenDefinitionsDocument openedDoc = openFileHelper(com);
    if (closeUntitled) {
      closeFileHelper(oldDoc);
    }
    setActiveDocument(openedDoc);
    setProjectChanged(true);
    return openedDoc;
  }
}
