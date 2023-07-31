class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("BuildException expected: directory already exists as a file");
    } catch (BuildException ex) {
    }
  }
}
