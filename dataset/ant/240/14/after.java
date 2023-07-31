class PlaceHold {
  @Test
  public void testNoJarNoClassname() {
    try {
      buildRule.executeTarget("testNoJarNoClassname");
      fail("Build exception should have been thrown - parameter validation");
    } catch (BuildException ex) {
      assertContains("Classname must not be null.", ex.getMessage());
    }
  }
}
