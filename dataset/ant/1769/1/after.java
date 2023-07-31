class PlaceHold {
  @Test
  public void testCircularReference() {
    try {
      buildRule.executeTarget("testCircularReference");
      fail("Did not throw exception on circular exception");
    } catch (BuildException e) {
      assertContains(
          "Circular definition not detected - ", "was circularly defined", e.getMessage());
    }
  }
}
