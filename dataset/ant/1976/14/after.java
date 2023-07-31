class PlaceHold {
  @Test
  public void testIsfalseTrue() {
    buildRule.executeTarget("isfalse-true");
    assertNull(buildRule.getProject().getProperty("isfalse-true"));
  }
}
