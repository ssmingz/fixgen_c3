class PlaceHold {
  void setFontDescription(long font) {
    if (labelHandle != 0) {
      OS.gtk_widget_modify_font(labelHandle, font);
    }
  }
}
