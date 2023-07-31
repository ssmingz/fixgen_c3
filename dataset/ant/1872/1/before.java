class PlaceHold {
  public void testNoRedirect() {
    expectLog(
        "no-redirect",
        ((getProject().getProperty("ant.file") + " out") + getProject().getProperty("ant.file"))
            + " err");
  }
}
