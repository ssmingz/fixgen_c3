class PlaceHold {
  @Test
  public void testEmpty() {
    try {
      buildRule.executeTarget("testEmpty");
      fail("BuildException expected: missing property");
    } catch (BuildException ex) {
      assertContains("property", ex.getMessage());
    }
  }
}
