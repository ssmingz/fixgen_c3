class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    int border = 0;
    if (fixedHandle != 0) {
      border += OS.gtk_container_get_border_width(fixedHandle);
    }
    if (scrolledHandle != 0) {
      border += OS.gtk_container_get_border_width(scrolledHandle);
    }
    int trimX = x - border;
    int trimY = y - border;
    int trimWidth = width + (border * 2);
    int trimHeight = height + (border * 2);
    trimHeight += hScrollBarWidth();
    trimWidth += vScrollBarWidth();
    if (scrolledHandle != 0) {
      if (OS.gtk_scrolled_window_get_shadow_type(scrolledHandle) != OS.GTK_SHADOW_NONE) {
        long style = OS.gtk_widget_get_style(scrolledHandle);
        int xthickness = OS.gtk_style_get_xthickness(style);
        int ythickness = OS.gtk_style_get_ythickness(style);
        trimX -= xthickness;
        trimY -= ythickness;
        trimWidth += xthickness * 2;
        trimHeight += ythickness * 2;
      }
    }
    return new Rectangle(trimX, trimY, trimWidth, trimHeight);
  }
}
