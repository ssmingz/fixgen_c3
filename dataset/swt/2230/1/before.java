class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    int selection = OS.GTK_CLIST_SELECTION(handle);
    if (selection == 0) {
      return 0;
    }
    return OS.g_list_length(selection);
  }
}
