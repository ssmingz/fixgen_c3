class PlaceHold {
  public void testCorrectTaskNameBadEl() {
    expectBuildExceptionContaining(
        "correct_taskname_badel", "element message", "javac doesn't support the");
  }
}
