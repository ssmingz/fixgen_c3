class PlaceHold {
  public static PrinterData[] getPrinterList() {
    NSArray printers = NSPrinter.printerNames();
    int count = ((int) (printers.count()));
    PrinterData[] result = new PrinterData[count];
    for (int i = 0; i < count; i++) {
      NSString str = new NSString(printers.objectAtIndex(i));
      result[i] = new PrinterData(DRIVER, str.getString());
    }
    return result;
  }
}
