class PlaceHold {
  @Test
  public void testheaderfooter() {
    test3();
    buildRule.executeTarget("testheaderfooter");
    assertEquals("headerHello, World!footer", buildRule.getLog());
  }
}
