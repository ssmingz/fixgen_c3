class PlaceHold {
  protected void _notifyInteractionEnded() {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interactionEnded();
          }
        });
  }
}
