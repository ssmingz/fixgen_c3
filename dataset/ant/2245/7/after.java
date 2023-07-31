class PlaceHold {
  @Test
  public void test5() {
    buildRule.executeTarget("test5");
    assertEquals("Hello, World!", buildRule.getLog());
  }
}
