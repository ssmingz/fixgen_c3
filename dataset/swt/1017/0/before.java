class PlaceHold {
  public static NSColor controlLightHighlightColor() {
    int result = OS.objc_msgSend(class_NSColor, sel_controlLightHighlightColor);
    return result != 0 ? new NSColor(result) : null;
  }
}
