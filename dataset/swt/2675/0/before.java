class PlaceHold {
  public int getSelectionIndex() {
    checkWidget();
    int index = 0;
    int result = -1;
    int children = OS.gtk_container_get_children(listHandle);
    int temp = children;
    while (temp != 0) {
      int item = OS.g_list_data(temp);
      if (OS.GTK_WIDGET_STATE(item) == OS.GTK_STATE_SELECTED) {
        result = index;
        break;
      }
      index++;
      temp = OS.g_list_next(temp);
    }
    OS.g_list_free(children);
    return result;
  }
}
