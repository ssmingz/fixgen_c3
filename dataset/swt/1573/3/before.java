class PlaceHold {
  boolean drawGripper(int x, int y, int width, int height, boolean vertical) {
    int paintHandle = paintHandle();
    int window = OS.GTK_WIDGET_WINDOW(paintHandle);
    if (window == 0) {
      return false;
    }
    int orientation = (vertical) ? OS.GTK_ORIENTATION_HORIZONTAL : OS.GTK_ORIENTATION_VERTICAL;
    if ((style & SWT.MIRRORED) != 0) {
      x = (getClientWidth() - width) - x;
    }
    OS.gtk_paint_handle(
        OS.gtk_widget_get_style(paintHandle),
        window,
        GTK_STATE_NORMAL,
        GTK_SHADOW_OUT,
        null,
        paintHandle,
        new byte[1],
        x,
        y,
        width,
        height,
        orientation);
    return true;
  }
}
