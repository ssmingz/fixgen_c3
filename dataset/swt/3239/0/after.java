class PlaceHold {
  public static NSColor controlDarkShadowColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_controlDarkShadowColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
