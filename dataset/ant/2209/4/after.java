class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("BuildException expected: manifest file does not exist");
    } catch (BuildException ex) {
    }
  }
}
