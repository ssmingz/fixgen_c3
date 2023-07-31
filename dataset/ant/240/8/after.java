class PlaceHold {
  @Test
  public void testCorrectTaskNameBadEl() {
    try {
      buildRule.executeTarget("correct_taskname_badel");
      fail("BuildException expected: element message");
    } catch (BuildException ex) {
      assertContains("javac doesn't support the", ex.getMessage());
    }
  }
}
