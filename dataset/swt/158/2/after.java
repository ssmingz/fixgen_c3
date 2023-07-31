class PlaceHold {
  public static NSArray printerNames() {
    long result = OS.objc_msgSend(class_NSPrinter, sel_printerNames);
    return result != 0 ? new NSArray(result) : null;
  }
}
