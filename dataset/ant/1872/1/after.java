class PlaceHold {
  public void testNoRedirect() {
    executeTarget("no-redirect");
    if (getProject().getProperty("test.can.run") == null) {
      return;
    }
    assertEquals(
        ((getProject().getProperty("ant.file") + " out") + getProject().getProperty("ant.file"))
            + " err",
        getLog());
  }
}
