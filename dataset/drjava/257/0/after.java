class PlaceHold {
  public void testSetBuildDirectory() throws MalformedProjectFileException, IOException {
    File f = new File("");
    _model.setBuildDirectory(f);
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            try {
              _model.openProject(_projFile);
            } catch (Exception e) {
              throw new UnexpectedException(e);
            }
          }
        });
    assertEquals("Build directory should not have been set", null, _model.getBuildDirectory());
    _model.setBuildDirectory(f);
    assertEquals("Build directory should have been set", f, _model.getBuildDirectory());
  }
}
