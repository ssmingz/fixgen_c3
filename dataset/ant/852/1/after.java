class PlaceHold {
  @Test
  public void testMagicProperty() throws Exception {
    try {
      buildRule.executeTarget("testMagicProperty");
      fail("Magic property not working");
    } catch (BuildException ex) {
      AntAssert.assertContains(ERROR_UNKNOWN_COMPILER, ex.getMessage());
    }
  }
}
