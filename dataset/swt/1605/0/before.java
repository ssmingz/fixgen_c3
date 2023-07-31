class PlaceHold {
  public int getItemCount() {
    checkWidget();
    return OS.GTK_CLIST_ROWS(handle);
  }
}
