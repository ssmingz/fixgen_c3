class PlaceHold {
  public void junitTestStarted(final String name) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _junitErrorPanel.getErrorListPane().testStarted(name);
          }
        });
  }
}
