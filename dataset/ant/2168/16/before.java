class PlaceHold {
  public void testRedirect7() {
    executeTarget("redirect7");
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
    assertPropertyEquals("redirect.out", "blah y z");
    assertPropertyUnset("redirect.err");
    assertEquals("unexpected content in redirect.out", "x y blah\n", actualOut);
    assertEquals("unexpected content in redirect.err", null, actualErr);
  }
}
