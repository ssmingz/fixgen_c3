class PlaceHold {
  public void testInteractionsLiveUpdateClasspath() throws BadLocationException, IOException {
    OpenDefinitionsDocument doc = setupDocument(FOO_TEXT);
    File f = tempFile();
    _doCompile(doc, f);
    String tempPath = f.getParent();
    File tempDir = new File(tempPath);
    tempDir.renameTo(new File(tempPath + "a"));
    String result = interpret("new DrJavaTestFoo().getClass().getName()");
    assertTrue(
        "interactions should have an error, not the correct answer",
        !"DrJavaTestFoo".equals(result));
    Vector<File> cp = new Vector<File>();
    cp.addElement(new File(tempPath + "a"));
    CONFIG.setSetting(EXTRA_CLASSPATH, cp);
    result = interpret("new DrJavaTestFoo().getClass().getName()");
    assertEquals("interactions result", "DrJavaTestFoo", result);
    tempDir = new File(tempPath + "a");
    tempDir.renameTo(new File(tempPath));
  }
}
