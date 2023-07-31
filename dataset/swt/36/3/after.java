class PlaceHold {
  void adjustTrim() {
    if (display.ignoreTrim) {
      return;
    }
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(shellHandle, allocation);
    int width = allocation.width;
    int height = allocation.height;
    long window = gtk_widget_get_window(shellHandle);
    GdkRectangle rect = new GdkRectangle();
    OS.gdk_window_get_frame_extents(window, rect);
    int trimWidth = Math.max(0, rect.width - width);
    int trimHeight = Math.max(0, rect.height - height);
    if ((trimWidth > MAXIMUM_TRIM) || (trimHeight > MAXIMUM_TRIM)) {
      display.ignoreTrim = true;
      return;
    }
    boolean hasTitle = false;
    boolean hasResize = false;
    boolean hasBorder = false;
    if ((style & SWT.NO_TRIM) == 0) {
      hasTitle = (style & (((SWT.MIN | SWT.MAX) | SWT.TITLE) | SWT.MENU)) != 0;
      hasResize = (style & SWT.RESIZE) != 0;
      hasBorder = (style & SWT.BORDER) != 0;
    }
    if (hasTitle) {
      if (hasResize) {
        display.titleResizeTrimWidth = trimWidth;
        display.titleResizeTrimHeight = trimHeight;
        return;
      }
      if (hasBorder) {
        display.titleBorderTrimWidth = trimWidth;
        display.titleBorderTrimHeight = trimHeight;
        return;
      }
      display.titleTrimWidth = trimWidth;
      display.titleTrimHeight = trimHeight;
      return;
    }
    if (hasResize) {
      display.resizeTrimWidth = trimWidth;
      display.resizeTrimHeight = trimHeight;
      return;
    }
    if (hasBorder) {
      display.borderTrimWidth = trimWidth;
      display.borderTrimHeight = trimHeight;
      return;
    }
  }
}
