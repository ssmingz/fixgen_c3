class PlaceHold {
  public void textSelectionChanged() {
    checkWidget();
    int stringRef = stringToStringRef(kAXSelectedTextChangedNotification);
    OS.AXNotificationHIObjectNotify(stringRef, control.handle, 0);
    OS.CFRelease(stringRef);
  }
}
