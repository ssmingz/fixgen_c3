class PlaceHold {
  public Color getForeground() {
    checkWidget();
    int[] ptr = new int[1];
    OS.gtk_tree_model_get(parent.modelHandle, handle, 2, ptr, -1);
    if (ptr[0] == 0) {
      return parent.getForeground();
    }
    GdkColor gdkColor = new GdkColor();
    OS.memmove(gdkColor, ptr[0], sizeof);
    return Color.gtk_new(display, gdkColor);
  }
}
