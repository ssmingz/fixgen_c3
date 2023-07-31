class PlaceHold {
  public static NSPrintInfo sharedPrintInfo() {
    long result = OS.objc_msgSend(class_NSPrintInfo, sel_sharedPrintInfo);
    return result != 0 ? new NSPrintInfo(result) : null;
  }
}
