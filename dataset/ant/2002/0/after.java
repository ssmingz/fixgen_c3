class PlaceHold {
  @Test
  public void testExceptingFoe() {
    try {
      buildRule.executeTarget("testExceptingFoe");
      fail("Build exception should have been thrown - " + "passes exception through");
    } catch (BuildException ex) {
      assertContains("Exception raised inside called program", ex.getMessage());
    }
  }
}
