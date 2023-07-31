class PlaceHold {
  int gtkPrinterFromPrinterData() {
    Callback printerCallback = new Callback(this, "GtkPrinterFunc_FindNamedPrinter", 2);
    int GtkPrinterFunc_FindNamedPrinter = printerCallback.getAddress();
    if (GtkPrinterFunc_FindNamedPrinter == 0) {
      SWT.error(ERROR_NO_MORE_CALLBACKS);
    }
    printer = 0;
    OS.gtk_enumerate_printers(GtkPrinterFunc_FindNamedPrinter, 0, 0, true);
    OS.gdk_threads_leave();
    printerCallback.dispose();
    return printer;
  }
}
