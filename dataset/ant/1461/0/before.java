class PlaceHold {
  public void testUserPropertyWinsNoInheritAll() {
    getProject().setUserProperty("test", "7");
    expectLogContaining("test-property-override-no-inheritall-start", "The value of test is 7");
  }
}
