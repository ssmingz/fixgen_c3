class PlaceHold {
  @Test
  public void testCorrectTaskNameBadAttr() {
    try {
      buildRule.executeTarget("correct_taskname_badattr");
      fail("BuildException expected: attribute message");
    } catch (BuildException ex) {
      assertContains("javac doesn't support the", ex.getMessage());
    }
  }
}
