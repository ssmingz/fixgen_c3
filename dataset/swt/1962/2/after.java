class PlaceHold {
  int getRowCount() {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener = accessibleTableListeners.get(i);
      listener.getRowCount(event);
    }
    return event.count;
  }
}
