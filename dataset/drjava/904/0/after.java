class PlaceHold {
  protected void _notifyInterpreterResetting() {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interpreterResetting();
          }
        });
  }
}
