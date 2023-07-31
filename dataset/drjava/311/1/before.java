class PlaceHold {
  protected void _notifySyntaxErrorOccurred(final int offset, final int length) {
    _notifier.interactionErrorOccurred(offset, length);
  }
}
