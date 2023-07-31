class PlaceHold {
  protected void setUp() throws IOException {
    _model = new GlobalModel();
    String user = System.getProperty("user.name");
    _tempDir = FileOps.createTempDirectory("DrJava-test-" + user);
  }
}
