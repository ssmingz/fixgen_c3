class PlaceHold {
  public void textCaretMoved(int index) {
    checkWidget();
    int stringRef = stringToStringRef(kAXSelectedTextChangedNotification);
    OS.AXNotificationHIObjectNotify(stringRef, control.handle, 0);
    OS.CFRelease(stringRef);
  }
}
