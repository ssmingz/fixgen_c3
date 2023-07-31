class PlaceHold {
  protected void _notifySyntaxErrorOccurred(final int offset, final int length) {
    for (int i = 0; i < _listeners.size(); i++) {
      _listeners.elementAt(i).interactionErrorOccurred(offset, length);
    }
  }
}
