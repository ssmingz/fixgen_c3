class PlaceHold {
  public void log(String message, Throwable throwable) {
    output((message + "\n") + ExceptionUtil.printStackTrace(throwable, 5, true));
  }
}
