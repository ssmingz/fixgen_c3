class PlaceHold {
  @Test
  public void testMultipleAssertions() {
    try {
      buildRule.executeTarget("test-multiple-assertions");
      fail("BuildException should have been thrown by assertion fail in task");
    } catch (BuildException ex) {
      assertContains(
          "multiple assertions rejected",
          "Only one assertion declaration is allowed",
          ex.getMessage());
    }
  }
}
