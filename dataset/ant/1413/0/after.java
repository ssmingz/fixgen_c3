class PlaceHold {
  @Test
  public void testOverrideWinsInheritAll() {
    buildRule.executeTarget("test-property-override-inheritall-start");
    AntAssert.assertContains("The value of test is 4", buildRule.getLog());
  }
}
