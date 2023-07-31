class PlaceHold {
  id getInsertionPointLineNumberAttribute(int childID) {
    id returnValue = null;
    if (accessibleTextExtendedListeners.size() > 0) {
      AccessibleTextExtendedEvent event = new AccessibleTextExtendedEvent(this);
      event.childID = childID;
      for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
        AccessibleTextExtendedListener listener =
            ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
        listener.getCaretOffset(event);
      }
      int caretOffset = event.offset;
      event.start = caretOffset;
      event.count = Integer.MIN_VALUE;
      event.type = ACC.TEXT_BOUNDARY_LINE;
      for (int i = 0; i < accessibleTextExtendedListeners.size(); i++) {
        AccessibleTextExtendedListener listener =
            ((AccessibleTextExtendedListener) (accessibleTextExtendedListeners.elementAt(i)));
        listener.getText(event);
      }
      returnValue = NSNumber.numberWithInt(Math.max(0, -event.count));
    } else {
      AccessibleControlEvent controlEvent = new AccessibleControlEvent(this);
      controlEvent.childID = childID;
      controlEvent.result = null;
      for (int i = 0; i < accessibleControlListeners.size(); i++) {
        AccessibleControlListener listener =
            ((AccessibleControlListener) (accessibleControlListeners.elementAt(i)));
        listener.getValue(controlEvent);
      }
      AccessibleTextEvent textEvent = new AccessibleTextEvent(this);
      textEvent.childID = childID;
      textEvent.offset = -1;
      for (int i = 0; i < accessibleTextListeners.size(); i++) {
        AccessibleTextListener listener =
            ((AccessibleTextListener) (accessibleTextListeners.elementAt(i)));
        listener.getCaretOffset(textEvent);
      }
      if ((controlEvent.result != null) && (textEvent.offset != (-1))) {
        int lineNumber = lineNumberForOffset(controlEvent.result, textEvent.offset);
        returnValue = NSNumber.numberWithInt(lineNumber);
      }
    }
    return returnValue;
  }
}
