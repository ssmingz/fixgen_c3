class PlaceHold {
  public void testfilterinline() {
    executeTarget("testfilterinline");
    assertTrue(getLog().indexOf("REPLACED") > (-1));
  }
}
