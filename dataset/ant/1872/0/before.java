class PlaceHold {
  public void testRedirect3() {
    expectLog("redirect3", getProject().getProperty("ant.file") + " err");
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
