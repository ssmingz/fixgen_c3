class PlaceHold {
  public void testRedirect2() {
    executeTarget("redirect2");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
    String expectedOut = getProject().getProperty("ant.file") + " out\n";
    String expectedErr = getProject().getProperty("ant.file") + " err\n";
    String actualOut = null;
    String actualErr = null;
    try {
      actualOut =
          FileUtils.newFileUtils()
              .readFully(new FileReader(getProject().resolveFile("redirect.out")));
      actualErr =
          FileUtils.newFileUtils()
              .readFully(new FileReader(getProject().resolveFile("redirect.err")));
    } catch (IOException eyeOhEx) {
    }
    assertEquals("unexpected output", expectedOut, actualOut);
    assertEquals("unexpected error output", expectedErr, actualErr);
  }
}
