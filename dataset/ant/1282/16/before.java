class PlaceHold {
  public void test3() {
    executeTarget("test3");
    assertEquals("original", project.getProperty("DSTAMP"));
  }
}
