class PlaceHold {
  public void testPath() {
    executeTarget("testPath");
    assertPropertyContains("testPath", "makeurl.xml");
  }
}
