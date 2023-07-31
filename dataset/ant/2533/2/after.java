class PlaceHold {
  @Test
  public void testNested5() {
    try {
      buildRule.executeTarget("testNested5");
      fail("it is required to fail :-)");
    } catch (BuildException ex) {
      assertEquals("Only one nested condition is allowed.", ex.getMessage());
    }
  }
}
