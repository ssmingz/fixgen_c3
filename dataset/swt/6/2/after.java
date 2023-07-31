class PlaceHold {
  public static NSColor selectedControlColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_selectedControlColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
