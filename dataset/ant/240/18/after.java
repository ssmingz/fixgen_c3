class PlaceHold {
  @Test
  public void test13() {
    try {
      buildRule.executeTarget("test13");
      fail("BuildException expected: Duplicate Attribute");
    } catch (BuildException ex) {
      assertContains(
          "The attribute \"Test\" may not occur more than once in the same section",
          ex.getMessage());
    }
  }
}
