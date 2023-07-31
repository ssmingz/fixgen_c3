class PlaceHold {
  @Test
  public void testUserPropertyWinsNoInheritAll() {
    buildRule.getProject().setUserProperty("test", "7");
    buildRule.executeTarget("test-property-override-no-inheritall-start");
    AntAssert.assertContains("The value of test is 7", buildRule.getLog());
  }
}
