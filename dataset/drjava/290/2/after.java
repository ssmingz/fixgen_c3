class PlaceHold {
  public void testSaveAllSaveCorrectFiles() throws BadLocationException, IOException {
    OpenDefinitionsDocument fooDoc = setupDocument(FOO_TEXT);
    OpenDefinitionsDocument barDoc = setupDocument(BAR_TEXT);
    OpenDefinitionsDocument trdDoc = setupDocument("third document contents");
    final File file1 = tempFile();
    final File file2 = tempFile();
    final File file3 = tempFile();
    fooDoc.setFile(file1);
    barDoc.setFile(file2);
    trdDoc.setFile(file3);
    final FileSelector fs = new FileSelector(file1);
    saveAllFiles(_model, fs);
    assertEquals("contents of saved file1", FOO_TEXT, IOUtil.toString(file1));
    assertEquals("contents of saved file2", BAR_TEXT, IOUtil.toString(file2));
    assertEquals("contents of saved file3", "third document contents", IOUtil.toString(file3));
    _log.log("testSaveAllSaveCorrectFiles completed");
  }
}
