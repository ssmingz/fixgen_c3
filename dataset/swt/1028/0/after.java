class PlaceHold {
  public void setGrayed(boolean grayed) {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return;
    }
    if (this.grayed == grayed) {
      return;
    }
    this.grayed = grayed;
    int[] ptr = new int[1];
    OS.gtk_tree_model_get(parent.modelHandle, handle, CHECKED_COLUMN, ptr, -1);
    OS.gtk_list_store_set(
        parent.modelHandle, handle, GRAYED_COLUMN, ptr[0] == 0 ? false : grayed, -1);
    cached = true;
  }
}
