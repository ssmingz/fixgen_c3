class PlaceHold {
  protected void expectOutputAndError(String target, String output, String error) {
    executeTarget(target);
    String realOutput = getOutput();
    assertEquals(output, realOutput);
    String realError = getError();
    assertEquals(error, realError);
  }
}
