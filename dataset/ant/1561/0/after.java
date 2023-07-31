class PlaceHold {
  @Test
  public void testExceptingFoeFork() {
    try {
      buildRule.executeTarget("testExceptingFoeFork");
      fail("Build exception should have been thrown - " + "exceptions turned into error codes");
    } catch (BuildException ex) {
      assertContains("Java returned:", ex.getMessage());
    }
  }
}
