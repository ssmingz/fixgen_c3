class PlaceHold {
  @Test
  public void testResourcenameWithLeadingSlash() {
    buildRule.executeTarget("testResourcenameWithLeadingSlash");
    assertNotNull(buildRule.getProject().getProperty("defaults"));
  }
}
