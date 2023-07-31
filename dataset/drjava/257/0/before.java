class PlaceHold {
  public void testSetBuildDirectory() throws MalformedProjectFileException, IOException {
    File f = new File("");
    _model.setBuildDirectory(f);
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    _model.openProject(_projFile);
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    _model.setBuildDirectory(f);
    assertEquals("Build directory should have been set", f, _model.getBuildDirectory());
  }
}
