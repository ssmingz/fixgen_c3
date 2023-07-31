class PlaceHold {
  @Test
  public void testThisIsNotACircularReference() {
    buildRule.executeTarget("thisIsNotACircularReference");
    assertContains("b is A/A/A", buildRule.getLog());
  }
}
