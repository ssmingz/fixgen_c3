class PlaceHold {
  id getHelpAttribute(int childID) {
    id returnValue = null;
    AccessibleEvent event = new AccessibleEvent(this);
    event.childID = childID;
    for (int i = 0; i < accessibleListenersSize(); i++) {
      AccessibleListener listener = ((AccessibleListener) (accessibleListeners.elementAt(i)));
      listener.getHelp(event);
    }
    if (event.result != null) {
      returnValue = NSString.stringWith(event.result);
    }
    return returnValue;
  }
}
