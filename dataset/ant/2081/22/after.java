class PlaceHold {
  @Test
  public void test3() {
    buildRule.executeTarget("test3");
    assertEquals("original", buildRule.getProject().getProperty("DSTAMP"));
  }
}
