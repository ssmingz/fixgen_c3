class PlaceHold {
  @Test
  public void testRunFailWithFailOnError() {
    try {
      buildRule.executeTarget("testRunFailWithFailOnError");
      fail("Build exception should have been thrown - " + "non zero return code");
    } catch (BuildException ex) {
      assertContains("Java returned:", ex.getMessage());
    }
  }
}
