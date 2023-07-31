class PlaceHold {
  void setFontDescription(int font) {
    super.setFontDescription(font);
    if (imageHeight != 0) {
      OS.gtk_widget_realize(handle);
      OS.gtk_clist_set_row_height(handle, 0);
      GtkCList clist = new GtkCList(handle);
      if (imageHeight > clist.row_height) {
        OS.gtk_clist_set_row_height(handle, imageHeight);
      }
    }
  }
}
