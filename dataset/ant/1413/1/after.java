class PlaceHold {
  @Test
  public void testOverrideWinsNoInheritAll() {
    buildRule.executeTarget("test-property-override-no-inheritall-start");
    AntAssert.assertContains("The value of test is 4", buildRule.getLog());
  }
}
