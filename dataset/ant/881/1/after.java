class PlaceHold {
  @Test
  public void test4() {
    try {
      buildRule.executeTarget("test4");
      fail("BuildException expected: tar cannot include itself");
    } catch (BuildException ex) {
    }
  }
}
