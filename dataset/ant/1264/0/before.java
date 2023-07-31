class PlaceHold {
  protected void expectOutput(String taskname, String output) {
    executeTarget(taskname);
    String realOutput = getOutput();
    assertEquals(output, realOutput);
  }
}
