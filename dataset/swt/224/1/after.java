class PlaceHold {
  public int getBorderWidth() {
    checkWidget();
    if ((style & SWT.MULTI) != 0) {
      return super.getBorderWidth();
    }
    long style = OS.gtk_widget_get_style(handle);
    if ((this.style & SWT.BORDER) != 0) {
      return OS.gtk_style_get_xthickness(style);
    }
    return 0;
  }
}
