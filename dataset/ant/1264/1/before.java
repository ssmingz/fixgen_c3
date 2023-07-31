class PlaceHold {
  protected void expectOutputAndError(String taskname, String output, String error) {
    executeTarget(taskname);
    String realOutput = getOutput();
    assertEquals(output, realOutput);
    String realError = getError();
    assertEquals(error, realError);
  }
}
