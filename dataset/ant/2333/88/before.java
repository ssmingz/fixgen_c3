class PlaceHold {
  public void testWorks() {
    executeTarget("testWorks");
    assertPropertyContains("testWorks", "file:");
    assertPropertyContains("testWorks", "/foo");
  }
}
