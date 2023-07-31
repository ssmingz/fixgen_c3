class PlaceHold {
  public static NSColor windowBackgroundColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_windowBackgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
