class PlaceHold {
  @Test
  public void testBadPattern() {
    try {
      buildRule.executeTarget("testBadPattern");
      fail("No parsing exception thrown");
    } catch (BuildException ex) {
      assertContains("Unparseable", ex.getMessage());
    }
  }
}
