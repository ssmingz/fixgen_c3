class PlaceHold {
  @Test
  public void test3() {
    try {
      buildRule.executeTarget("test3");
      fail("BuildException expected: zip cannot include itself");
    } catch (BuildException ex) {
    }
  }
}
