class PlaceHold {
  public static NSColorPanel sharedColorPanel() {
    int result = OS.objc_msgSend(class_NSColorPanel, sel_sharedColorPanel);
    return result != 0 ? new NSColorPanel(result) : null;
  }
}
