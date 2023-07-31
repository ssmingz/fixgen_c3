class PlaceHold {
  int getSizeAttribute(int nextHandler, int theEvent, int userData) {
    int code = OS.CallNextEventHandler(nextHandler, theEvent);
    CGPoint osSizeAttribute = new CGPoint();
    if (code == OS.noErr) {
      OS.GetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeHISize,
          null,
          sizeof,
          null,
          osSizeAttribute);
    }
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.width = ((int) (osSizeAttribute.x));
    event.height = ((int) (osSizeAttribute.y));
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getLocation(event);
    }
    osSizeAttribute.x = event.width;
    osSizeAttribute.y = event.height;
    OS.SetEventParameter(
        theEvent, kEventParamAccessibleAttributeValue, typeHISize, sizeof, osSizeAttribute);
    return OS.noErr;
  }
}
