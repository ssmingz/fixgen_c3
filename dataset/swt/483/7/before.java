class PlaceHold {
  public static PrinterData getDefaultPrinterData() {
    NSPrinter printer = NSPrintInfo.defaultPrinter();
    NSString str = printer.name();
    char[] buffer = new char[str.length()];
    str.getCharacters_(buffer);
    return new PrinterData(DRIVER, new String(buffer));
  }
}
