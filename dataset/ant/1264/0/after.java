class PlaceHold {
  protected void expectOutput(String target, String output) {
    executeTarget(target);
    String realOutput = getOutput();
    assertEquals(output, realOutput);
  }
}
