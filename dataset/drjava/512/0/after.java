class PlaceHold {
  protected void _notifyInteractionIncomplete() {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interactionIncomplete();
          }
        });
  }
}
