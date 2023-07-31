class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    if ((style & SWT.BALLOON) != 0) {
      return OS.GTK_WIDGET_VISIBLE(handle);
    }
    if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
      int tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(handle);
      return OS.GTK_WIDGET_VISIBLE(tipWindow);
    }
    return false;
  }
}
