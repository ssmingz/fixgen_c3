class PlaceHold {
  public void testUnsavedAndUnCompiledChanges() throws Exception {
    if (printMessages) {
      System.out.println("----testUnsavedAndUnCompiledChanges-----");
    }
    OpenDefinitionsDocument doc = setupDocument(MONKEYTEST_PASS_TEXT);
    final File file = new File(_tempDir, "MonkeyTestPass.java");
    doc.saveFile(new FileSelector(file));
    TestShouldSucceedListener listener = new TestShouldSucceedListener();
    _model.addListener(listener);
    synchronized (listener) {
      doc.startCompile();
      listener.wait();
    }
    changeDocumentText(MONKEYTEST_FAIL_TEXT, doc);
    synchronized (listener) {
      doc.startJUnit();
      listener.wait();
    }
    _model.removeListener(listener);
    assertEquals(
        "test case should have no errors reported after modifying",
        0,
        doc.getJUnitErrorModel().getNumErrors());
    doc.saveFile(new FileSelector(file));
    listener = new TestShouldSucceedListener();
    _model.addListener(listener);
    synchronized (listener) {
      doc.startJUnit();
      listener.wait();
    }
    assertEquals(
        "test case should have no errors reported after saving",
        0,
        doc.getJUnitErrorModel().getNumErrors());
  }
}
