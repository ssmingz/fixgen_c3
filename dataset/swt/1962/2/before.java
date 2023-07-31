class PlaceHold {
  int getRowCount() {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.getRowCount(event);
    }
    return event.count;
  }
}
