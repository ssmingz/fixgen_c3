class PlaceHold {
  @Override
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    Rectangle trim = super.computeTrim(x, y, width, height);
    int border = 0;
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.SHELL_TRIM)) == 0) {
      border = OS.gtk_container_get_border_width(shellHandle);
    }
    if (isCustomResize()) {
      border = OS.gtk_container_get_border_width(shellHandle);
    }
    int trimWidth = trimWidth();
    int trimHeight = trimHeight();
    trim.x -= (trimWidth / 2) + border;
    trim.y -= (trimHeight - (trimWidth / 2)) + border;
    trim.width += trimWidth + (border * 2);
    trim.height += trimHeight + (border * 2);
    if (menuBar != null) {
      forceResize();
      GtkAllocation allocation = new GtkAllocation();
      OS.gtk_widget_get_allocation(handle, allocation);
      int menuBarHeight = allocation.height;
      trim.y -= menuBarHeight;
      trim.height += menuBarHeight;
    }
    return trim;
  }
}
