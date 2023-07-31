class PlaceHold {
  @Test
  public void testImplicitOptional() {
    buildRule.executeTarget("implicit.optional");
    assertEquals("Before implicitAfter implicit", buildRule.getLog());
  }
}
