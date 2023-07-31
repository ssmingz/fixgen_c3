class PlaceHold {
  id getNumberOfCharactersAttribute(int childID) {
    id returnValue = null;
    AccessibleTextEvent event = new AccessibleTextEvent(this);
    event.count = -1;
    for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
      AccessibleTextExtendedListener listener =
          ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
      listener.getCharacterCount(event);
    }
    if (event.count != (-1)) {
      AccessibleControlEvent e = new AccessibleControlEvent(this);
      e.childID = ACC.CHILDID_SELF;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getRole(e);
        listener.getValue(e);
      }
      event.count = ((e.detail == ACC.ROLE_TEXT) && (e.result != null)) ? e.result.length() : 0;
      returnValue = NSNumber.numberWithInt(event.count);
    }
    return returnValue;
  }
}
