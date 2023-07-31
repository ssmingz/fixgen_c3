class PlaceHold {
  int getColumnCount() {
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListeners.size(); i++) {
      AccessibleTableListener listener =
          ((AccessibleTableListener) (accessibleTableListeners.elementAt(i)));
      listener.getColumnCount(event);
    }
    return event.count;
  }
}
