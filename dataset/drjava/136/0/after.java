class PlaceHold {
  public void addListener(GlobalModelListener listener) {
    synchronized (_listeners) {
      _listeners.addLast(listener);
    }
  }
}
