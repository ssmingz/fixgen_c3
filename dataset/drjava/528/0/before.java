class PlaceHold {
  public void junitSuiteStarted(final int numTests) {
    assert EventQueue.isDispatchThread();
    _junitErrorPanel.progressReset(numTests);
  }
}
