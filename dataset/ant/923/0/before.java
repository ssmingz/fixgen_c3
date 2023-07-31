class PlaceHold {
  public void log(String message, Throwable throwable) {
    output((message + "\n") + StringUtil.printStackTrace(throwable, 5, true));
  }
}
