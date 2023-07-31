class PlaceHold {
  @Test
  public void testText() {
    try {
      buildRule.executeTarget("testText");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("testText", ex.getMessage());
    }
  }
}
