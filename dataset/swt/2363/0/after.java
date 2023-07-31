class PlaceHold {
  void setFontDescription(int font) {
    super.setFontDescription(font);
    if (imageHeight != 0) {
      OS.gtk_widget_realize(handle);
      OS.gtk_clist_set_row_height(handle, 0);
      if (imageHeight > OS.GTK_CLIST_ROW_HEIGHT(handle)) {
        OS.gtk_clist_set_row_height(handle, imageHeight);
      }
    }
  }
}
