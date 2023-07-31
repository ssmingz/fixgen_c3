class PlaceHold {
  protected void _notifyInteractionStarted() {
    for (int i = 0; i < _listeners.size(); i++) {
      _listeners.elementAt(i).interactionStarted();
    }
  }
}
