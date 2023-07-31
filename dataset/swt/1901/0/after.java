class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    GdkColor gdkColor = (color != null) ? color.handle : null;
    OS.gtk_list_store_set(parent.modelHandle, handle, BACKGROUND_COLUMN, gdkColor, -1);
  }
}
