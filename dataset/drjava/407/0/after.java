class PlaceHold {
  public OpenDefinitionsDocument[] openFiles(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    boolean closeUntitled = _hasOneEmptyDocument();
    if (!closeUntitled) {
      addToBrowserHistory();
    }
    OpenDefinitionsDocument oldDoc = _activeDocument;
    OpenDefinitionsDocument[] openedDocs = openFilesHelper(com);
    if (openedDocs.length > 0) {
      if (closeUntitled) {
        closeFileHelper(oldDoc);
      }
      setActiveDocument(openedDocs[0]);
    }
    return openedDocs;
  }
}
