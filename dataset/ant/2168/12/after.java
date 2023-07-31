class PlaceHold {
  public void testRedirect1() {
    executeTarget("redirect1");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
    String expectedOut =
        ((getProject().getProperty("ant.file") + " out\n") + getProject().getProperty("ant.file"))
            + " err\n";
    String actualOut = null;
    try {
      actualOut =
          FileUtils.newFileUtils()
              .readFully(new FileReader(getProject().resolveFile("redirect.out")));
    } catch (IOException eyeOhEx) {
    }
    assertEquals("unexpected output", expectedOut, actualOut);
  }
}
