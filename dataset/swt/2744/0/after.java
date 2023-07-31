class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    GdkColor gdkColor = (color != null) ? color.handle : null;
    OS.gtk_tree_store_set(parent.modelHandle, handle, 2, gdkColor, -1);
  }
}
