class PlaceHold {
  public void testDeleteFileBeforeCloseAll()
      throws IOException, OperationCanceledException, AlreadyOpenException {
    final File[] files = new File[10];
    for (int i = 0; i < 10; i++) {
      String txt = "Text for file " + i;
      files[i] = writeToNewTempFile(txt);
    }
    FileOpenSelector fos =
        new FileOpenSelector() {
          public File[] getFiles() throws OperationCanceledException {
            return files;
          }
        };
    _sdModel.openFiles(fos);
    OpenDefinitionsDocument doc = _sdModel.getDefinitionsDocuments().get(5);
    _sdModel.setActiveDocument(doc);
    files[5].delete();
    _sdModel.closeAllFiles();
  }
}
