class PlaceHold {
  protected void _notifySyntaxErrorOccurred(final int offset, final int length) {
    for (int i = 0; i < _listeners.size(); i++) {
      _listeners.get(i).interactionErrorOccurred(offset, length);
    }
  }
}
