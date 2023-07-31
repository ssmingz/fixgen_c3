class PlaceHold {
  public void testDeleteFileWhileOpen()
      throws IOException, OperationCanceledException, AlreadyOpenException {
    String txt = "This is some test text";
    File f = writeToNewTempFile(txt);
    OpenDefinitionsDocument doc1 = _model.openFile(new FileSelector(f));
    @SuppressWarnings("unused")
    OpenDefinitionsDocument doc2 = _model.newFile();
    f.delete();
    _model.closeFile(doc1);
    _log.log("testDeleteFileWhileOpen completed");
  }
}
