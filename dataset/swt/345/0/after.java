class PlaceHold {
  public int getBorderWidth() {
    checkWidget();
    long style = OS.gtk_widget_get_style(handle);
    if ((this.style & SWT.BORDER) != 0) {
      return OS.gtk_style_get_xthickness(style);
    }
    return 0;
  }
}
