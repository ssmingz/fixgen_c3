class PlaceHold {
  public void setChecked(boolean checked) {
    checkWidget();
    OS.gtk_list_store_set(parent.modelHandle, handle, 2 * parent.MAX_COLUMNS, checked, -1);
  }
}
