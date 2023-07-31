class PlaceHold {
  public void setChecked(boolean checked) {
    checkWidget();
    OS.gtk_list_store_set(parent.modelHandle, handle, 0, checked, -1);
  }
}
