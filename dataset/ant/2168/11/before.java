class PlaceHold {
  private void testRedirect5or6(String target) {
    executeTarget(target);
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
    assertPropertyEquals("redirect.err", "");
    assertEquals("unexpected content in redirect.out", "blah y z\nx blah z\nx y blah\n", actualOut);
    assertEquals("unexpected content in redirect.err", null, actualErr);
  }
}
