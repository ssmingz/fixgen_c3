class PlaceHold {
  @Test
  public void testForkedCapture() throws IOException {
    buildRule.getProject().setProperty("fork", "true");
    testNonForkedCapture();
    assertNoPrint(buildRule.getOutput(), "output");
    assertNoPrint(buildRule.getError(), "error output");
    assertOutput();
  }
}
