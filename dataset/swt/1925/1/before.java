class PlaceHold {
  public Color getBackground() {
    checkWidget();
    int[] ptr = new int[1];
    OS.gtk_tree_model_get(parent.modelHandle, handle, 3, ptr, -1);
    if (ptr[0] == 0) {
      return parent.getBackground();
    }
    GdkColor gdkColor = new GdkColor();
    OS.memmove(gdkColor, ptr[0], sizeof);
    return Color.gtk_new(display, gdkColor);
  }
}
