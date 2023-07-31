class PlaceHold {
  @Test
  public void testImplicit() {
    buildRule.executeTarget("implicit");
    assertEquals("Before implicitIn implicitAfter implicit", buildRule.getLog());
  }
}
