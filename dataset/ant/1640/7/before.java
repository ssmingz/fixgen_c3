class PlaceHold {
  protected void notifyTestEnded(String testname) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).testEnded(testname);
      }
    }
  }
}
