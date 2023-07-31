class PlaceHold {
  public void addFailure(Test test, Throwable t) {
    String testName = test.toString();
    String trace = StringUtils.getStackTrace(t);
    writer.notifyTestFailed(STATUS_FAILURE, testName, trace);
  }
}
