class PlaceHold {
  public void addFailure(Test test, Throwable t) {
    log("Adding failure for test: " + test);
    String testName = test.toString();
    String trace = StringUtils.getStackTrace(t);
    writer.notifyTestFailed(STATUS_FAILURE, testName, trace);
  }
}
