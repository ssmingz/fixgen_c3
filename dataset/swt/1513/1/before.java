class PlaceHold {
  int getPositionAttribute(int nextHandler, int theEvent, int userData) {
    int code = OS.CallNextEventHandler(nextHandler, theEvent);
    CGPoint osPositionAttribute = new CGPoint();
    if (code == OS.noErr) {
      OS.GetEventParameter(
          theEvent, kEventParamMouseLocation, typeHIPoint, null, sizeof, null, osPositionAttribute);
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.x = ((int) (osPositionAttribute.x));
    event.y = ((int) (osPositionAttribute.y));
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getLocation(event);
    }
    osPositionAttribute.x = event.x;
    osPositionAttribute.y = event.y;
    OS.SetEventParameter(
        theEvent, kEventParamAccessibleAttributeValue, typeHIPoint, sizeof, osPositionAttribute);
    return OS.noErr;
  }
}
