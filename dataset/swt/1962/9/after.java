class PlaceHold {
  id getVisibleRowsAttribute(int childID) {
    if (accessibleTableListenersSize() == 0) {
      return null;
    }
    id returnValue = null;
    AccessibleTableEvent event = new AccessibleTableEvent(this);
    for (int i = 0; i < accessibleTableListenersSize(); i++) {
      AccessibleTableListener listener = accessibleTableListeners.get(i);
      listener.getVisibleRows(event);
    }
    if (event.accessibles != null) {
      NSMutableArray array = NSMutableArray.arrayWithCapacity(event.accessibles.length);
      Accessible[] accessibles = event.accessibles;
      for (int i = 0; i < accessibles.length; i++) {
        Accessible acc = accessibles[i];
        array.addObject(acc.delegate);
      }
      returnValue = array;
    }
    return returnValue == null ? NSArray.array() : returnValue;
  }
}
