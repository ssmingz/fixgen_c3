class PlaceHold {
  @Test
  public void testIstrueFalse() {
    buildRule.executeTarget("istrue-false");
    assertNull(buildRule.getProject().getProperty("istrue-false"));
  }
}
