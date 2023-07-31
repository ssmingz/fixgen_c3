class PlaceHold {
  public void addListener(GlobalModelListener listener) {
    synchronized (_listeners) {
      _listeners.addElement(listener);
    }
  }
}
