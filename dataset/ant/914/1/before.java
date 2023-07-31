class PlaceHold {
  protected void notifyTestFailed(int kind, String testname, String trace) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).testFailed(kind, testname, trace);
      }
    }
  }
}
