class PlaceHold {
  public void testSrcDirTest() {
    expectBuildException("srcDirTest", "Src cannot be a directory.");
  }
}
