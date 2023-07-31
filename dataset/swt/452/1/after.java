class PlaceHold {
  public static NSColor windowFrameTextColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_windowFrameTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
