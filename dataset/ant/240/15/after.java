class PlaceHold {
  @Test
  public void testTaskCase() {
    try {
      buildRule.executeTarget("taskcase");
      fail("Build exception should have been thrown due to case sensitivity of name");
    } catch (BuildException ex) {
      assertContains(
          "Task names should be case sensitive",
          "Problem: failed to create task or type ecHO",
          ex.getMessage());
    }
  }
}
