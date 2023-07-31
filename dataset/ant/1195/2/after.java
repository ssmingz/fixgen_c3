class PlaceHold {
  @Test
  public void testNsCurrent() {
    buildRule.executeTarget("ns.current");
    assertEquals("Echo2 inside a macroHello from x:p", buildRule.getLog());
  }
}
