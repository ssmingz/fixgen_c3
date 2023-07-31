class PlaceHold {
  protected void _notifyInterpreterReady() {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interpreterReady();
          }
        });
  }
}
