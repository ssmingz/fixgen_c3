class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((parent.style & SWT.CHECK) != 0) {
      return;
    }
    this.image = image;
    int pixmap = 0;
    int mask = 0;
    if (image != null) {
      pixmap = image.pixmap;
      mask = image.mask;
    }
    int ctree = parent.handle;
    byte[] spacing = new byte[1];
    boolean[] is_leaf = new boolean[1];
    boolean[] expanded = new boolean[1];
    byte[] buffer = Converter.wcsToMbcs(null, text, true);
    OS.gtk_ctree_get_node_info(
        ctree, handle, null, spacing, null, null, null, null, is_leaf, expanded);
    OS.gtk_ctree_set_node_info(
        ctree, handle, buffer, spacing[0], pixmap, mask, pixmap, mask, is_leaf[0], expanded[0]);
    if (image != null) {
      if (parent.imageHeight == 0) {
        int[] width = new int[1];
        int[] height = new int[1];
        OS.gdk_drawable_get_size(pixmap, width, height);
        if (height[0] > OS.GTK_CLIST_ROW_HEIGHT(parent.handle)) {
          parent.imageHeight = height[0];
          OS.gtk_clist_set_row_height(ctree, height[0]);
        }
      }
    }
  }
}
