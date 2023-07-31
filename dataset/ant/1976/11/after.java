class PlaceHold {
  @Test
  public void testFailOnError() throws BuildException {
    buildRule.executeTarget("testFailOnError");
    assertNull(buildRule.getProject().getProperty("testFailOnError"));
  }
}
