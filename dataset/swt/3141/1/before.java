class PlaceHold {
  public static NSColor selectedTextBackgroundColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_selectedTextBackgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
