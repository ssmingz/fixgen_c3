class PlaceHold {
  public void setLocation(int x, int y) {
    checkWidget();
    this.x = x;
    this.y = y;
    if ((style & SWT.BALLOON) != 0) {
      if (gtk_widget_get_visible(handle)) {
        configure();
      }
    } else if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
      int tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(handle);
      if (gtk_widget_get_visible(tipWindow)) {
        OS.gtk_window_move(tipWindow, x, y);
      }
    }
  }
}
