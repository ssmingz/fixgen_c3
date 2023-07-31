class PlaceHold {
  public void testNoFileJUnitNoFrames() {
    executeTarget("reports1");
    if (new File(getOutputDir(), "html/junit-noframes.html").exists()) {
      fail("No file junit-noframes.html expected");
    }
  }
}
