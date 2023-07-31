class PlaceHold {
  public void deselectAll() {
    checkWidget();
    blockSignal(handle, Selection);
    if ((style & SWT.SINGLE) != 0) {
      int selection = OS.GTK_CLIST_SELECTION(handle);
      if ((selection != 0) && (OS.g_list_length(selection) > 0)) {
        int index = OS.GTK_CLIST_FOCUS_ROW(handle);
        if (index == (-1)) {
          index = 0;
        }
        OS.gtk_clist_select_row(handle, index, 0);
        OS.gtk_clist_unselect_row(handle, index, 0);
      }
    } else {
      OS.gtk_ctree_unselect_recursive(handle, 0);
    }
    unblockSignal(handle, Selection);
  }
}
