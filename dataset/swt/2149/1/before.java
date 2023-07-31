class PlaceHold {
  void setFontDescription(long font) {
    super.setFontDescription(font);
    OS.gtk_widget_modify_font(labelHandle, font);
  }
}
