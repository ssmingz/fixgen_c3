class PlaceHold {
  public void testRedirect3() {
    executeTarget("redirect3");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
    assertEquals(getProject().getProperty("ant.file") + " err", getLog());
    String expectedOut = getProject().getProperty("ant.file") + " out\n";
    String actualOut = null;
    try {
      actualOut =
          FileUtils.newFileUtils()
              .readFully(new FileReader(getProject().resolveFile("redirect.out")));
    } catch (IOException eyeOhEx) {
    }
    assertEquals("unexpected output", expectedOut, actualOut);
    assertPropertyEquals("redirect.out", expectedOut.trim());
  }
}
