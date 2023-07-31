class PlaceHold {
  id getVisibleCharacterRangeAttribute(int childID) {
    NSRange range = null;
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener = accessibleTextExtendedListeners.get(i);
        listener.getVisibleRanges(event);
      }
      range = new NSRange();
      range.location = event.start;
      range.length = event.end - event.start;
    } else if (accessibleControlListenersSize() > 0) {
      AccessibleControlEvent event = new AccessibleControlEvent(this);
      event.childID = childID;
      event.result = null;
      for (int i = 0; i < accessibleControlListenersSize(); i++) {
        AccessibleControlListener listener = accessibleControlListeners.get(i);
        listener.getValue(event);
      }
      if (event.result != null) {
        range = new NSRange();
        range.location = 0;
        range.length = event.result.length();
      }
    }
    return range != null ? NSValue.valueWithRange(range) : null;
  }
}
