class PlaceHold {
  @Test
  public void testfilterinline() {
    buildRule.executeTarget("testfilterinline");
    assertTrue(buildRule.getLog().indexOf("REPLACED") > (-1));
  }
}
