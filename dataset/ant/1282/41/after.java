class PlaceHold {
  @Test
  public void testEvalProps() throws BuildException {
    buildRule.executeTarget("testEvalProps");
    if (buildRule.getProject().getProperty("testEvalProps").indexOf("rain") < 0) {
      fail("property eval broken");
    }
  }
}
