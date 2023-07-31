class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if (((state & BACKGROUND) == 0) && (color == null)) {
      return;
    }
    GdkColor gdkColor = null;
    if (color != null) {
      if (color.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      gdkColor = color.handle;
    }
    boolean set = false;
    if (gdkColor == null) {
      long style = OS.gtk_widget_get_modifier_style(handle);
      set = (OS.gtk_rc_style_get_color_flags(style, GTK_STATE_NORMAL) & OS.GTK_RC_BG) != 0;
    } else {
      GdkColor oldColor = getBackgroundColor();
      set = oldColor.pixel != gdkColor.pixel;
    }
    if (set) {
      if (color == null) {
        state &= ~BACKGROUND;
      } else {
        state |= BACKGROUND;
      }
      setBackgroundColor(gdkColor);
      redrawChildren();
    }
  }
}
