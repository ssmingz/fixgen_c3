class PlaceHold {
  public boolean getChecked() {
    checkWidget();
    if ((parent.style & SWT.CHECK) == 0) {
      return false;
    }
    int[] ptr = new int[1];
    OS.gtk_tree_model_get(parent.modelHandle, handle, 0, ptr, -1);
    return ptr[0] != 0;
  }
}
