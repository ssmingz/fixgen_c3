class PlaceHold {
  @Test
  public void test6() {
    buildRule.executeTarget("test6");
    assertEquals("simpletask: worked", buildRule.getLog());
  }
}
