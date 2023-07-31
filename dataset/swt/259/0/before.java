class PlaceHold {
  public void textSelectionChanged() {
    checkWidget();
    int stringRef = stringToStringRef(kAXSelectedChildrenChangedNotification);
    OS.AXNotificationHIObjectNotify(stringRef, control.handle, 0);
    OS.CFRelease(stringRef);
  }
}
