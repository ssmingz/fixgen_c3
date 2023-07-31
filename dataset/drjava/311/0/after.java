class PlaceHold {
  protected void _notifyInterpreterChanged(final boolean inProgress) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interpreterChanged(inProgress);
          }
        });
  }
}
