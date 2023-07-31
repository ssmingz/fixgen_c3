class PlaceHold {
  protected void notifyTestStarted(String testname) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).onTestStarted(testname);
      }
    }
  }
}
