class PlaceHold {
  @Test
  public void testGlobal() {
    buildRule.executeTarget("testGlobal");
    assertEquals("worked", buildRule.getLog());
  }
}
