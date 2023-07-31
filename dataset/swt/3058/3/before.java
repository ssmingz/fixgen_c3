class PlaceHold {
  public void select(int index) {
    checkWidget();
    if ((index < 0) || (index >= items.length)) {
      return;
    }
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.gtk_combo_box_set_active(handle, index);
      OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    } else {
      ignoreSelect = true;
      OS.gtk_list_select_item(listHandle, index);
      ignoreSelect = false;
    }
  }
}
