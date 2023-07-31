class PlaceHold {
  public void testResourcename() {
    executeTarget("testResourcename");
    assertNotNull(getProject().getProperty("defaults"));
  }
}
