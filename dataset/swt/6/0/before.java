class PlaceHold {
  public static NSColor selectedControlTextColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_selectedControlTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
