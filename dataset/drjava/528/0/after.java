class PlaceHold {
  public void junitSuiteStarted(final int numTests) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _junitErrorPanel.progressReset(numTests);
          }
        });
  }
}
