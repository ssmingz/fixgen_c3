class PlaceHold {
  protected void notifyTestSuiteStopped(long elapsedtime) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).onRunStopped(elapsedtime);
      }
    }
  }
}
