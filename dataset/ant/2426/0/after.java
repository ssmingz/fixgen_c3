class PlaceHold {
  @Test
  public void testNoResourceOnErrorFailAll() {
    try {
      buildRule.executeTarget("noresourcefailall");
      fail("BuildException expected: the requested resource does not exist");
    } catch (BuildException ex) {
      assertContains("Could not load definitions from resource ", ex.getMessage());
    }
  }
}
