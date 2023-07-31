class PlaceHold {
  public void setUp() throws IOException {
    DrJava.getConfig().resetToDefaults();
    createModel();
    String user = System.getProperty("user.name");
    _tempDir = FileOps.createTempDirectory("DrJava-test-" + user);
    super.setUp();
  }
}
