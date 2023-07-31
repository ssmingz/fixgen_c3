class PlaceHold {
  @Test
  public void testNested3() {
    try {
      buildRule.executeTarget("testNested3");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("testNested3", ex.getMessage());
    }
  }
}
