class PlaceHold {
  protected void _notifySyntaxErrorOccurred(final int offset, final int length) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.interactionErrorOccurred(offset, length);
          }
        });
  }
}
