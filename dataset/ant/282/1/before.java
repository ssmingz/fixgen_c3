class PlaceHold {
  public void testCorrectTaskNameBadAttr() {
    expectBuildExceptionContaining(
        "correct_taskname_badattr", "attribute message", "javac doesn't support the");
  }
}
