class PlaceHold {
  public void testFilter() {
    executeTarget("testfilter");
    assertTrue(getLog().indexOf("REPLACED") > (-1));
  }
}
