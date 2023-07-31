class PlaceHold {
  public static PrinterData[] getPrinterList() {
    printerList = new PrinterData[0];
    if ((OS.GTK_VERSION < OS.VERSION(2, 10, 0)) || disablePrinting) {
      return printerList;
    }
    gtk_init();
    Callback printerCallback = new Callback(Printer.class, "GtkPrinterFunc_List", 2);
    int GtkPrinterFunc_List = printerCallback.getAddress();
    if (GtkPrinterFunc_List == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gtk_enumerate_printers(GtkPrinterFunc_List, 0, 0, true);
    OS.gdk_threads_leave();
    printerCallback.dispose();
    return printerList;
  }
}
