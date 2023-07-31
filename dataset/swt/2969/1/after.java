class PlaceHold {
  void register() {
    super.register();
    if ((style & SWT.BALLOON) == 0) {
      if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
        long tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(handle);
        if (tipWindow != 0) {
          display.addWidget(tipWindow, this);
        }
      }
    }
  }
}
