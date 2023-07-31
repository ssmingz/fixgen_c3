class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("Build exception expected: SMTP auth only possibly with MIME mail");
    } catch (BuildException ex) {
    }
  }
}
