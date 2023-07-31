class PlaceHold {
  @Test
  public void testClassname() {
    buildRule.executeTarget("testClassname");
    assertNotNull(buildRule.getProject().getProperty("antmain"));
  }
}
