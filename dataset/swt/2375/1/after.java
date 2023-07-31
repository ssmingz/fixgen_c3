class PlaceHold {
  public static PrinterData getDefaultPrinterData() {
    NSPrinter printer = NSPrintInfo.defaultPrinter();
    if (printer == null) {
      return null;
    }
    NSString str = printer.name();
    return new PrinterData(DRIVER, str.getString());
  }
}
