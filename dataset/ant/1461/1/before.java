class PlaceHold {
  public void testUserPropertyWinsInheritAll() {
    getProject().setUserProperty("test", "7");
    expectLogContaining("test-property-override-inheritall-start", "The value of test is 7");
  }
}
