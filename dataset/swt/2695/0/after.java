class PlaceHold {
  public Point getSize() {
    checkWidget();
    int width = OS.GTK_WIDGET_WIDTH(vboxHandle);
    int height = OS.GTK_WIDGET_HEIGHT(vboxHandle);
    int border = 0;
    if ((style & ((SWT.NO_TRIM | SWT.BORDER) | SWT.SHELL_TRIM)) == 0) {
      border = OS.gtk_container_get_border_width(shellHandle);
    }
    return new Point((width + trimWidth()) + (2 * border), (height + trimHeight()) + (2 * border));
  }
}
