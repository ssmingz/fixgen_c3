class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("BuildException expected: Unrecognized whenempty attribute: format C: /y");
    } catch (BuildException ex) {
    }
  }
}
