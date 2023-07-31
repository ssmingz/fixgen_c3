class PlaceHold {
  @Test
  public void testClassNotFound() {
    try {
      buildRule.executeTarget("classNotFound");
      fail("BuildException expected: classname specified doesn't exist");
    } catch (BuildException ex) {
    }
  }
}
