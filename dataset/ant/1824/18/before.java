class PlaceHold {
  public void testResourcenameWithLeadingSlash() {
    executeTarget("testResourcenameWithLeadingSlash");
    assertNotNull(getProject().getProperty("defaults"));
  }
}
