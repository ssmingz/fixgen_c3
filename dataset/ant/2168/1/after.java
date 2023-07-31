class PlaceHold {
  public void testRedirect4() {
    executeTarget("redirect4");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
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
    File x = getProject().resolveFile("x");
    File y = getProject().resolveFile("y");
    File z = getProject().resolveFile("z");
    int xout = actualOut.indexOf(x + " out");
    int yout = actualOut.indexOf(y + " out");
    int zout = actualOut.indexOf(z + " out");
    int xerr = actualErr.indexOf(x + " err");
    int yerr = actualErr.indexOf(y + " err");
    int zerr = actualErr.indexOf(z + " err");
    assertFalse("xout < 0", xout < 0);
    assertFalse("yout < 0", yout < 0);
    assertFalse("zout < 0", zout < 0);
    assertFalse("xerr < 0", xerr < 0);
    assertFalse("yerr < 0", yerr < 0);
    assertFalse("zerr < 0", zerr < 0);
    assertFalse("yout < xout", yout < xout);
    assertFalse("zout < yout", zout < yout);
    assertFalse("yerr < xerr", yerr < xerr);
    assertFalse("zerr < yerr", zerr < yerr);
    assertPropertyEquals("redirect.out", x + " out");
    assertPropertyEquals("redirect.err", x + " err");
  }
}
