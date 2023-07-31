class PlaceHold {
  public void testSetBuildDirectory() {
    File f = new File("");
    _model.setBuildDirectory(f);
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    try {
      _model.openProject(_testFile);
    } catch (IOException ioe) {
      fail("Should not have thrown an IOException when opening a temporary file");
    }
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    _model.setBuildDirectory(f);
    assertEquals("Build directory should have been set", f, _model.getBuildDirectory());
  }
}
