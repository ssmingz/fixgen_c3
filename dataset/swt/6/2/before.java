class PlaceHold {
  public static NSColor selectedControlColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_selectedControlColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
