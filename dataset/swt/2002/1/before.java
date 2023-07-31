class PlaceHold {
  public NSAppleEventDescriptor initListDescriptor() {
    int result = OS.objc_msgSend(this.id, sel_initListDescriptor);
    return result == this.id ? this : result != 0 ? new NSAppleEventDescriptor(result) : null;
  }
}
