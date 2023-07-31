class PlaceHold {
  protected void notifyTestSuiteStarted(int count) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).testRunStarted(count);
      }
    }
  }
}
