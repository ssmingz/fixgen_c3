class PlaceHold {
  id getSelectedTextRangeAttribute(int childID) {
    id returnValue = null;
    if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.index = 0;
      for (int i = 0; i < accessibleTextExtendedListenersSize(); i++) {
        AccessibleTextExtendedListener listener = accessibleTextExtendedListeners.get(i);
        listener.getSelection(event);
      }
      NSRange range = new NSRange();
      range.location = event.start;
      range.length = event.end - event.start;
      returnValue = NSValue.valueWithRange(range);
    } else if (accessibleTextExtendedListenersSize() > 0) {
      AccessibleTextEvent event = new AccessibleTextEvent(this);
      event.childID = childID;
      event.offset = -1;
      event.length = 0;
      for (int i = 0; i < accessibleTextListenersSize(); i++) {
        AccessibleTextListener listener = accessibleTextListeners.get(i);
        listener.getSelectionRange(event);
      }
      if (event.offset != (-1)) {
        NSRange range = new NSRange();
        range.location = event.offset;
        range.length = event.length;
        returnValue = NSValue.valueWithRange(range);
      }
    }
    return returnValue;
  }
}
