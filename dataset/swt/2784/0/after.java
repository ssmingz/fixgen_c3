class PlaceHold {
  void deregister() {
    super.deregister();
    if ((style & SWT.BALLOON) == 0) {
      if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
        int tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(handle);
        if (tipWindow != 0) {
          display.removeWidget(tipWindow);
        }
      }
    }
  }
}
