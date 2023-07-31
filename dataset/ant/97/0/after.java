class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("BuildException expected: zipfile must not point to a directory");
    } catch (BuildException ex) {
    }
  }
}
