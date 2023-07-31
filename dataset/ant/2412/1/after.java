class PlaceHold {
  @Test
  public void test1() {
    try {
      buildRule.executeTarget("test1");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("No message", ex.getMessage());
    }
  }
}
