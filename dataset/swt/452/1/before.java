class PlaceHold {
  public static NSColor windowFrameTextColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_windowFrameTextColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
