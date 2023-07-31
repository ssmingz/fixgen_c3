class PlaceHold {
  @Test
  public void testResourcename() {
    buildRule.executeTarget("testResourcename");
    assertNotNull(buildRule.getProject().getProperty("defaults"));
  }
}
