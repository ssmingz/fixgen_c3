class PlaceHold {
  public void addError(Test test, Throwable t) {
    log("Adding error for test: " + test);
    String testName = test.toString();
    String trace = StringUtils.getStackTrace(t);
    writer.notifyTestFailed(STATUS_ERROR, testName, trace);
  }
}
