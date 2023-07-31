class PlaceHold {
  @Test
  public void testAntTypeTest() {
    buildRule.executeTarget("antTypeTest");
    assertEquals("", buildRule.getLog());
  }
}
