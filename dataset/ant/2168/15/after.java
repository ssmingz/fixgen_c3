class PlaceHold {
  public void testRedirect7() {
    executeTarget("redirect7");
    if (getProject().getProperty("wc.can.run") == null) {
      return;
    }
    String expectedOut = getProject().getProperty("ant.file") + " out\n";
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
    assertEquals("unexpected output", "3", actualOut.trim());
    assertEquals(getProject().getProperty("redirect.out").trim(), "3");
    assertEquals("unexpected error output", null, actualErr);
  }
}
