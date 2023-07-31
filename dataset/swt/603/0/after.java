class PlaceHold {
  id getDescriptionAttribute(int childID) {
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = childID;
    event.result = null;
    id returnValue = null;
    for (int i = 0; i < accessibleListenersSize(); i++) {
      AccessibleListener listener = accessibleListeners.get(i);
      listener.getDescription(event);
    }
    returnValue = (event.result != null) ? NSString.stringWith(event.result) : null;
    if (returnValue == null) {
      if (control instanceof Composite) {
        returnValue = NSString.string();
      }
    }
    return returnValue;
  }
}
