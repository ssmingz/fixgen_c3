class PlaceHold {
  public void setUp() throws IOException {
    String user = System.getProperty("user.name");
    _tempDir = FileOps.createTempDirectory("DrJava-test-" + user);
    _history = new History();
    DrJava.getConfig().resetToDefaults();
  }
}
