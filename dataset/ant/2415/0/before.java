class PlaceHold {
  public void addError(Test test, Throwable t) {
    String testName = test.toString();
    String trace = StringUtils.getStackTrace(t);
    writer.notifyTestFailed(STATUS_ERROR, testName, trace);
  }
}
