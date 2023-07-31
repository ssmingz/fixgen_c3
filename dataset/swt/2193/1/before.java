class PlaceHold {
  public static PrinterData getDefaultPrinterData() {
    findData = null;
    if ((OS.GTK_VERSION < OS.VERSION(2, 10, 0)) || disablePrinting) {
      return null;
    }
    gtk_init();
    Callback printerCallback = new Callback(Printer.class, "GtkPrinterFunc_Default", 2);
    int GtkPrinterFunc_Default = printerCallback.getAddress();
    if (GtkPrinterFunc_Default == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gtk_enumerate_printers(GtkPrinterFunc_Default, 0, 0, true);
    OS.gdk_threads_leave();
    printerCallback.dispose();
    return findData;
  }
}
