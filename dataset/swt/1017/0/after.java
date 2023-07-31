class PlaceHold {
  public static NSColor controlLightHighlightColor() {
    long result = OS.objc_msgSend(class_NSColor, sel_controlLightHighlightColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
