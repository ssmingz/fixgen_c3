class PlaceHold {
  protected void _notifyInteractionEnded() {
    for (int i = 0; i < _listeners.size(); i++) {
      _listeners.elementAt(i).interactionEnded();
    }
  }
}
