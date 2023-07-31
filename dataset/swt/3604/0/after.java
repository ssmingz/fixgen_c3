class PlaceHold {
  public void setChecked(boolean checked) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    OS.gtk_list_store_set(parent.modelHandle, handle, CHECKED_COLUMN, checked, -1);
    OS.gtk_list_store_set(parent.modelHandle, handle, GRAYED_COLUMN, !checked ? false : grayed, -1);
    cached = true;
  }
}
