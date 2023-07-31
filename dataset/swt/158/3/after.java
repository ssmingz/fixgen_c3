class PlaceHold {
  public static NSOpenPanel openPanel() {
    long result = OS.objc_msgSend(class_NSOpenPanel, sel_openPanel);
    return result != 0 ? new NSOpenPanel(result) : null;
  }
}
