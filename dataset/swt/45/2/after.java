class PlaceHold {
  public static NSColorPanel sharedColorPanel() {
    long result = OS.objc_msgSend(class_NSColorPanel, sel_sharedColorPanel);
    return result != 0 ? new NSColorPanel(result) : null;
  }
}
