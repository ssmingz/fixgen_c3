class PlaceHold {
  static DeviceData checkNull(PrinterData data) {
    if (data == null) {
      data = new PrinterData();
    }
    if ((data.driver == null) || (data.name == null)) {
      PrinterData defaultPrinter = getDefaultPrinterData();
      data.driver = defaultPrinter.driver;
      data.name = defaultPrinter.name;
    }
    return data;
  }
}
