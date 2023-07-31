class PlaceHold {
  int getNumberOfCharactersAttribute(int nextHandler, int theEvent, int userData) {
    int code = userData;
    AccessibleControlEvent event = new AccessibleControlEvent(this);
    event.childID = getChildIDFromEvent(theEvent);
    event.result = null;
    for (int i = 0; i < accessibleControlListeners.size(); i++) {
      AccessibleControlListener listener =
          ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
      listener.getValue(event);
    }
    String appValue = event.result;
    if (appValue != null) {
      OS.SetEventParameter(
          theEvent,
          kEventParamAccessibleAttributeValue,
          typeSInt32,
          4,
          new int[] {appValue.length()});
      code = OS.noErr;
    }
    return code;
  }
}
