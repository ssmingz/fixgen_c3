class PlaceHold {
  GdkColor getFgColor() {
    if (OS.GTK3) {
      return getContextColor();
    }
    long fontHandle = fontHandle();
    OS.gtk_widget_realize(fontHandle);
    GdkColor color = new GdkColor();
    OS.gtk_style_get_fg(OS.gtk_widget_get_style(fontHandle), GTK_STATE_NORMAL, color);
    return color;
  }
}
