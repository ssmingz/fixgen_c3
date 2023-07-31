class PlaceHold {
  @Test
  public void test12() {
    try {
      buildRule.executeTarget("test12");
      fail("BuildException expected: Section with no name");
    } catch (BuildException ex) {
      assertContains("Sections must have a name", ex.getMessage());
    }
  }
}
