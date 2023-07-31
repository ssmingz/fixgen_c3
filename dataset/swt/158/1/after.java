class PlaceHold {
  public static NSSavePanel savePanel() {
    long result = OS.objc_msgSend(class_NSOpenPanel, sel_savePanel);
    return result != 0 ? new NSSavePanel(result) : null;
  }
}
