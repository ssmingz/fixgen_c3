class PlaceHold {
  public void removeListener(GlobalModelListener listener) {
    synchronized (_listeners) {
      _listeners.removeElement(listener);
    }
  }
}
