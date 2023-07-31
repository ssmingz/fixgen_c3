class PlaceHold {
  protected void notifyTestSystemProperties(Properties props) {
    synchronized (listeners) {
      for (int i = 0; i < listeners.size(); i++) {
        ((TestRunListener) (listeners.elementAt(i))).onTestRunSystemProperties(props);
      }
    }
  }
}
