class PlaceHold {
  public static NSColor controlShadowColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_controlShadowColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
