class PlaceHold {
  public void testReopenFile() throws BadLocationException, IOException {
    final File tempFile = writeToNewTempFile(BAR_TEXT);
    TestListener listener =
        new TestListener() {
          public void fileOpened(OpenDefinitionsDocument doc) {
            File file = null;
            try {
              file = doc.getFile();
            } catch (IllegalStateException ise) {
              fail("file does not yet exist");
            }
            assertEquals("file to open", tempFile, file);
            openCount++;
          }
        };
    _model.addListener(listener);
    try {
      OpenDefinitionsDocument doc = _model.openFile(new FileSelector(tempFile));
      listener.assertOpenCount(1);
      assertModified(false, doc);
      assertContents(BAR_TEXT, doc);
    } catch (AlreadyOpenException aoe) {
      fail("File was already open!");
    } catch (OperationCanceledException oce) {
      fail("Open was unexpectedly canceled!");
    }
    try {
      OpenDefinitionsDocument doc2 = _model.openFile(new FileSelector(tempFile));
      fail("file should already be open");
    } catch (AlreadyOpenException aoe) {
      listener.assertOpenCount(1);
    } catch (OperationCanceledException oce) {
      fail("Open was unexpectedly canceled!");
    }
  }
}
