class PlaceHold {
  public PrinterData open() {
    PrinterData data = null;
    NSPrintPanel panel = NSPrintPanel.printPanel();
    NSPrintInfo printInfo = new NSPrintInfo(NSPrintInfo.sharedPrintInfo().copy());
    NSMutableDictionary dict = printInfo.dictionary();
    if (printToFile) {
      dict.setValue(NSPrintSaveJob, NSPrintJobDisposition);
    }
    dict.setValue(NSNumber.numberWithBool(scope == PrinterData.ALL_PAGES), NSPrintAllPages);
    if (scope == PrinterData.PAGE_RANGE) {
      dict.setValue(NSNumber.numberWithInt(startPage), NSPrintFirstPage);
      dict.setValue(NSNumber.numberWithInt(endPage), NSPrintLastPage);
    }
    panel.setOptions(OS.NSPrintPanelShowsPageSetupAccessory | panel.options());
    if (panel.runModalWithPrintInfo(printInfo) != OS.NSCancelButton) {
      NSPrinter printer = printInfo.printer();
      NSString str = printer.name();
      data = new PrinterData(Printer.DRIVER, str.getString());
      data.printToFile = printInfo.jobDisposition().isEqual(NSPrintSaveJob);
      if (data.printToFile) {
        NSString filename = new NSString(dict.objectForKey(NSPrintSavePath));
        data.fileName = filename.getString();
      }
      data.scope =
          (new NSNumber(dict.objectForKey(NSPrintAllPages)).intValue() != 0)
              ? PrinterData.ALL_PAGES
              : PrinterData.PAGE_RANGE;
      if (data.scope == PrinterData.PAGE_RANGE) {
        data.startPage = new NSNumber(dict.objectForKey(NSPrintFirstPage)).intValue();
        data.endPage = new NSNumber(dict.objectForKey(NSPrintLastPage)).intValue();
      }
      data.collate = new NSNumber(dict.objectForKey(NSPrintMustCollate)).intValue() != 0;
      data.copyCount = new NSNumber(dict.objectForKey(NSPrintCopies)).intValue();
      NSData nsData = NSKeyedArchiver.archivedDataWithRootObject(printInfo);
      data.otherData = new byte[nsData.length()];
      OS.memmove(data.otherData, nsData.bytes(), data.otherData.length);
      printToFile = data.printToFile;
      scope = data.scope;
      startPage = data.startPage;
      endPage = data.endPage;
    }
    printInfo.release();
    return data;
  }
}
