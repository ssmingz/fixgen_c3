class PlaceHold {
  public void testForceFileOpen()
      throws BadLocationException, IOException, OperationCanceledException, AlreadyOpenException {
    final File tempFile1 = writeToNewTempFile(FOO_TEXT);
    final File tempFile2 = writeToNewTempFile(BAR_TEXT);
    TestListener listener =
        new TestListener() {
          public void fileOpened(OpenDefinitionsDocument doc) {
            File file = null;
            try {
              file = doc.getFile();
            } catch (IllegalStateException ise) {
              fail("file does not exist");
            }
            openCount++;
          }
        };
    _model.addListener(listener);
    OpenDefinitionsDocument doc = _model.openFile(new FileSelector(tempFile1));
    listener.assertOpenCount(1);
    assertModified(false, doc);
    assertContents(FOO_TEXT, doc);
    OpenDefinitionsDocument doc1 = _model.getDocumentForFile(tempFile1);
    listener.assertOpenCount(1);
    assertEquals("opened document", doc, doc1);
    assertContents(FOO_TEXT, doc1);
    OpenDefinitionsDocument doc2 = _model.getDocumentForFile(tempFile2);
    listener.assertOpenCount(2);
    assertContents(BAR_TEXT, doc2);
  }
}
