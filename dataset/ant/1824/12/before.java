class PlaceHold {
  public void testUpdateNotNecessary() {
    executeTarget("testUpdateNotNecessary");
    assertEquals(-1, getLog().indexOf("Updating"));
  }
}
