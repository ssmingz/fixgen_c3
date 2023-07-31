class PlaceHold {
  public OpenDefinitionsDocument openFiles(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    boolean closeUntitled = _hasOneEmptyDocument();
    OpenDefinitionsDocument oldDoc = _activeDocument;
    OpenDefinitionsDocument openedDoc = openFilesHelper(com);
    if (closeUntitled) {
      closeFileHelper(oldDoc);
    }
    setActiveDocument(openedDoc);
    return openedDoc;
  }
}
