class PlaceHold {
  public static NSColor windowFrameColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_windowFrameColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
