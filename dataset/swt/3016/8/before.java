class PlaceHold {
  public static PrinterData[] getPrinterList() {
    printerList = new PrinterData[0];
    if ((OS.GTK_VERSION < OS.VERSION(2, 10, 0)) || disablePrinting) {
      return printerList;
    }
    if (!OS.g_thread_supported()) {
      OS.g_thread_init(0);
    }
    OS.gtk_set_locale();
    if (!OS.gtk_init_check(new int[] {0}, null)) {
      SWT.error(ERROR_NO_HANDLES, null, " [gtk_init_check() failed]");
    }
    Callback printerCallback = new Callback(Printer.class, "GtkPrinterFunc_List", 2);
    int GtkPrinterFunc_List = printerCallback.getAddress();
    if (GtkPrinterFunc_List == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gtk_enumerate_printers(GtkPrinterFunc_List, 0, 0, true);
    printerCallback.dispose();
    return printerList;
  }
}
