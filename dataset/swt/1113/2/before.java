class PlaceHold {
  public void redraw(int x, int y, int width, int height, boolean all) {
    checkWidget();
    if (!gtk_widget_get_visible(topHandle())) {
      return;
    }
    if ((style & SWT.MIRRORED) != 0) {
      x = (getClientWidth() - width) - x;
    }
    redrawWidget(x, y, width, height, false, all, false);
  }
}
