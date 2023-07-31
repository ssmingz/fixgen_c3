class PlaceHold {
  @Test
  public void testUndefined() {
    try {
      buildRule.executeTarget("testUndefined");
      fail("Build exception expected: left out the name attribute");
    } catch (BuildException ex) {
      AntAssert.assertContains("No type specified", ex.getMessage());
    }
  }
}
