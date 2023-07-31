class PlaceHold {
  @Test
  public void test2() {
    try {
      buildRule.executeTarget("test2");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("test2", ex.getMessage());
    }
  }
}
