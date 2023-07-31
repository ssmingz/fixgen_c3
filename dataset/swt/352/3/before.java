class PlaceHold {
  void setFontDescription(int font) {
    OS.gtk_widget_modify_font(labelHandle, font);
    OS.gtk_widget_modify_font(pixmapHandle, font);
  }
}
