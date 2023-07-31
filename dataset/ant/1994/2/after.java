class PlaceHold {
  @Test
  public void testBlankTarget() {
    try {
      buildRule.executeTarget("blank-target");
      fail("target name must not be empty");
    } catch (BuildException ex) {
    }
  }
}
