class PlaceHold {
  Color _getBackground() {
    long[] ptr = new long[1];
    OS.gtk_tree_model_get(parent.modelHandle, handle, BACKGROUND_COLUMN, ptr, -1);
    if (ptr[0] == 0) {
      return parent.getBackground();
    }
    GdkColor gdkColor = new GdkColor();
    OS.memmove(gdkColor, ptr[0], sizeof);
    OS.gdk_color_free(ptr[0]);
    return Color.gtk_new(display, gdkColor);
  }
}
