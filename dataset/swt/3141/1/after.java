class PlaceHold {
  public static NSColor selectedTextBackgroundColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_selectedTextBackgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
