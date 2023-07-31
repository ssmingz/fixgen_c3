class PlaceHold {
  public static NSColor controlShadowColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_controlShadowColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
