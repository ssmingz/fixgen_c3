class PlaceHold {
  public static NSColor textBackgroundColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_textBackgroundColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
