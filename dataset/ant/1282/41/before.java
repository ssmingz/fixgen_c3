class PlaceHold {
  public void testEvalProps() throws BuildException {
    executeTarget("testEvalProps");
    if (project.getProperty("testEvalProps").indexOf("rain") < 0) {
      fail("property eval broken");
    }
  }
}
