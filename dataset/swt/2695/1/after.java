class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    int[] x = new int[1];
    int[] y = new int[1];
    OS.gtk_window_get_position(shellHandle, x, y);
    int width = OS.GTK_WIDGET_WIDTH(vboxHandle);
    int height = OS.GTK_WIDGET_HEIGHT(vboxHandle);
    int border = 0;
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.SHELL_TRIM)) == 0) {
      border = OS.gtk_container_get_border_width(shellHandle);
    }
    return new Rectangle(
        x[0], y[0], (width + trimWidth()) + (2 * border), (height + trimHeight()) + (2 * border));
  }
}
