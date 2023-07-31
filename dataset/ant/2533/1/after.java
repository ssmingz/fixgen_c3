class PlaceHold {
  @Test
  public void testNested6() {
    try {
      buildRule.executeTarget("testNested6");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("testNested6\ntestNested6\ntestNested6", ex.getMessage());
    }
  }
}
