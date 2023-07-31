class PlaceHold {
  public void junitTestStarted(final String name) {
    assert EventQueue.isDispatchThread();
    _junitErrorPanel.getErrorListPane().testStarted(name);
  }
}
