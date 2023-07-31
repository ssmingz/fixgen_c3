class PlaceHold {
  public static NSPrintOperation printOperationWithView(NSView view, NSPrintInfo printInfo) {
    int result =
        OS.objc_msgSend(
            class_NSPrintOperation,
            sel_printOperationWithView_printInfo_,
            view != null ? view.id : 0,
            printInfo != null ? printInfo.id : 0);
    return result != 0 ? new NSPrintOperation(result) : null;
  }
}
