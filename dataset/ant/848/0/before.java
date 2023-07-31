class PlaceHold {
  public void testForkedCapture() throws IOException {
    getProject().setProperty("fork", "true");
    testNonForkedCapture();
    assertNoPrint(getOutput(), "output");
    assertNoPrint(getError(), "error output");
    assertOutput();
  }
}
