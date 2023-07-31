class PlaceHold {
  public OpenDefinitionsDocument openFiles(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    boolean closeUntitled = _hasOneEmptyDocument();
    OpenDefinitionsDocument oldDoc = _activeDocument;
    OpenDefinitionsDocument openedDoc = super.openFiles(com);
    if (closeUntitled) {
      super.closeFile(oldDoc);
    }
    setActiveDocument(openedDoc);
    return openedDoc;
  }
}
