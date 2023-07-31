class PlaceHold {
  @Test
  public void test4b() {
    try {
      buildRule.executeTarget("test4b");
      fail("target doesn't exist");
    } catch (BuildException ex) {
    }
  }
}
