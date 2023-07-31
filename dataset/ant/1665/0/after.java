class PlaceHold {
  @Test
  public void testRunFailFoeFork() {
    try {
      buildRule.executeTarget("testRunFailFoeFork");
      fail("Build exception should have been thrown - " + "java failures being propagated");
    } catch (BuildException ex) {
      assertContains("Java returned:", ex.getMessage());
    }
  }
}
