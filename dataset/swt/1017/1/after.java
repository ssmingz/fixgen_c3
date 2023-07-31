class PlaceHold {
  public static NSColor controlHighlightColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_controlHighlightColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
