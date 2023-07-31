class PlaceHold {
  public OpenDefinitionsDocument openFile(FileOpenSelector com)
      throws IOException, OperationCanceledException, AlreadyOpenException {
    DefinitionsDocument tempDoc = ((DefinitionsDocument) (_editorKit.createDefaultDocument()));
    try {
      final File file = com.getFile().getAbsoluteFile();
      OpenDefinitionsDocument openDoc = _getOpenDocument(file);
      if (openDoc != null) {
        throw new AlreadyOpenException(openDoc);
      }
      FileReader reader = new FileReader(file);
      _editorKit.read(reader, tempDoc, 0);
      reader.close();
      tempDoc.setFile(file);
      tempDoc.resetModification();
      final OpenDefinitionsDocument doc = new DefinitionsDocumentHandler(tempDoc);
      _definitionsDocs.addElement(doc);
      notifyListeners(
          new EventNotifier() {
            public void notifyListener(GlobalModelListener l) {
              l.fileOpened(doc);
            }
          });
      return doc;
    } catch (BadLocationException docFailed) {
      throw new UnexpectedException(docFailed);
    }
  }
}
