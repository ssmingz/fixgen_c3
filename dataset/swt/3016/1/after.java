class PlaceHold {
  public static PrinterData getDefaultPrinterData() {
    printerList = new PrinterData[1];
    if ((OS.GTK_VERSION < OS.VERSION(2, 10, 0)) || disablePrinting) {
      return null;
    }
    if (!OS.g_thread_supported()) {
      OS.g_thread_init(0);
    }
    OS.gtk_set_locale();
    if (!OS.gtk_init_check(new int[] {0}, null)) {
      SWT.error(ERROR_NO_HANDLES, null, " [gtk_init_check() failed]");
    }
    Callback printerCallback = new Callback(Printer.class, "GtkPrinterFunc_Default", 2);
    int GtkPrinterFunc_Default = printerCallback.getAddress();
    if (GtkPrinterFunc_Default == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    OS.gtk_enumerate_printers(GtkPrinterFunc_Default, 0, 0, true);
    OS.gdk_threads_leave();
    printerCallback.dispose();
    return printerList[0];
  }
}
