class PlaceHold {
  protected void notifyTestSuiteEnded(long elapsedtime) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).onRunEnded(elapsedtime);
      }
    }
  }
}
