class PlaceHold {
  public void setUp() throws IOException {
    DrJava.getConfig().resetToDefaults();
    createModel();
    _model.setResetAfterCompile(false);
    String user = System.getProperty("user.name");
    _tempDir = FileOps.createTempDirectory("DrJava-test-" + user);
    super.setUp();
  }
}
