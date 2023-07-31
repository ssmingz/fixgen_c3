class PlaceHold {
  public void setGrayed(boolean grayed) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    OS.gtk_list_store_set(parent.modelHandle, handle, GRAYED_COLUMN, grayed, -1);
    cached = true;
  }
}
