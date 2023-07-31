class PlaceHold {
  @Test
  public void testUserPropertyWinsInheritAll() {
    buildRule.getProject().setUserProperty("test", "7");
    buildRule.executeTarget("test-property-override-inheritall-start");
    AntAssert.assertContains("The value of test is 7", buildRule.getLog());
  }
}
