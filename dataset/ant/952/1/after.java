class PlaceHold {
  @Test
  public void testNoCrash() {
    buildRule.executeTarget("nocrash");
    assertNull(buildRule.getProject().getProperty("crashed"));
  }
}
