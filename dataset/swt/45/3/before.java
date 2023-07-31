class PlaceHold {
  public static NSFontPanel sharedFontPanel() {
    int result = OS.objc_msgSend(class_NSFontPanel, sel_sharedFontPanel);
    return result != 0 ? new NSFontPanel(result) : null;
  }
}
