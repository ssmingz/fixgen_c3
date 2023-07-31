class PlaceHold {
  @Test
  public void testIstrue() {
    buildRule.executeTarget("istrue");
    assertEquals("true", buildRule.getProject().getProperty("istrue"));
  }
}
