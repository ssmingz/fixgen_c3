class PlaceHold {
  public void testCaseInsensitive() {
    executeTarget("testCaseInsensitive");
    assertTrue("works outside of container", getLog().indexOf("hello ") > (-1));
    assertTrue("works inside of container", getLog().indexOf("world") > (-1));
  }
}
