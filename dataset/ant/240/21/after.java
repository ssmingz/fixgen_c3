class PlaceHold {
  @Test
  public void testNoProperty() {
    try {
      buildRule.executeTarget("testNoProperty");
      fail("BuildException expected: missing property");
    } catch (BuildException ex) {
      assertContains("property", ex.getMessage());
    }
  }
}
