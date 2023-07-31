class PlaceHold {
  @Test
  public void testsame() {
    try {
      buildRule.executeTarget("samefile");
      fail("Build exception should have been thrown - output file same as input");
    } catch (BuildException ex) {
    }
  }
}
