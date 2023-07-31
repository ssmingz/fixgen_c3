class PlaceHold {
  public void testRedirect4() {
    executeTarget("redirect4");
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
    assertPropertyEquals("redirect.out", expectedOut.trim());
    assertEquals("unexpected error output", expectedErr, actualErr);
    assertPropertyEquals("redirect.err", expectedErr.trim());
  }
}
