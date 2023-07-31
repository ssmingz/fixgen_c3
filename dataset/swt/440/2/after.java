class PlaceHold {
  public int getSelectionIndex() {
    checkWidget();
    return OS.gtk_notebook_get_current_page(handle);
  }
}
