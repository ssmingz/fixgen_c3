class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("Build exception expected: SSL only possibly with MIME mail");
    } catch (BuildException ex) {
    }
  }
}
