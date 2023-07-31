class PlaceHold {
  public void testNoRedirect() {
    executeTarget("no-redirect");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
    String log = getLog();
    File x = getProject().resolveFile("x");
    File y = getProject().resolveFile("y");
    File z = getProject().resolveFile("z");
    int xout = log.indexOf(x + " out");
    int yout = log.indexOf(y + " out");
    int zout = log.indexOf(z + " out");
    int xerr = log.indexOf(x + " err");
    int yerr = log.indexOf(y + " err");
    int zerr = log.indexOf(z + " err");
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
  }
}
