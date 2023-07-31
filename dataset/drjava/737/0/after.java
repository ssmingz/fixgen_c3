class PlaceHold {
  public void testCloseUnmodifiedAutomatically()
      throws BadLocationException, IOException, OperationCanceledException, AlreadyOpenException {
    assertNumOpenDocs(1);
    OpenDefinitionsDocument doc = getSDModel().getActiveDocument();
    assertModified(false, doc);
    assertLength(0, doc);
    final File tempFile = writeToNewTempFile(BAR_TEXT);
    SDTestListener listener =
        new SDTestListener() {
          public void fileOpened(OpenDefinitionsDocument doc) {
            File file = null;
            try {
              file = doc.getFile();
            } catch (IllegalStateException ise) {
              fail("file does not exist");
            } catch (FileMovedException fme) {
              fail("file does not exist");
            }
            assertEquals("file to open", tempFile, file);
            openCount++;
          }

          public void fileClosed(OpenDefinitionsDocument doc) {
            closeCount++;
          }

          public void activeDocumentChanged(OpenDefinitionsDocument doc) {
            switchCount++;
          }
        };
    getSDModel().addListener(listener);
    doc = getSDModel().openFile(new FileSelector(tempFile));
    listener.assertOpenCount(1);
    listener.assertCloseCount(1);
    listener.assertSwitchCount(1);
    assertNumOpenDocs(1);
    assertModified(false, doc);
    assertContents(BAR_TEXT, doc);
    getSDModel().removeListener(listener);
  }
}
