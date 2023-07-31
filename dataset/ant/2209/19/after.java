class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("BuildException expected: classname specified doesn't exist");
    } catch (BuildException ex) {
    }
  }
}
