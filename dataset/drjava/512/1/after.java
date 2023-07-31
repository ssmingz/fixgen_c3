class PlaceHold {
  protected void _notifyInteractionStarted() {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interactionStarted();
          }
        });
  }
}
