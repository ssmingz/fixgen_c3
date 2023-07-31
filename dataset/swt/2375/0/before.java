class PlaceHold {
  public static PrinterData[] getPrinterList() {
    NSArray printers = NSPrinter.printerNames();
    int count = printers.count();
    PrinterData[] result = new PrinterData[count];
    for (int i = 0; i < count; i++) {
      NSString str = new NSString(printers.objectAtIndex(i));
      char[] buffer = new char[str.length()];
      str.getCharacters_(buffer);
      result[i] = new PrinterData(DRIVER, new String(buffer));
    }
    return result;
  }
}
