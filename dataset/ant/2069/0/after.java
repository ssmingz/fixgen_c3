class PlaceHold {
  @Test
  public void testNested1() {
    try {
      buildRule.executeTarget("testNested1");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("condition satisfied", ex.getMessage());
    }
  }
}
