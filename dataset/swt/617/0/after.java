class PlaceHold {
  public void setSelection(boolean selected) {
    checkWidget();
    if ((style & ((SWT.CHECK | SWT.RADIO) | SWT.TOGGLE)) == 0) {
      return;
    }
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CLICKED);
    OS.gtk_toggle_button_set_active(handle, selected);
    if ((style & SWT.CHECK) != 0) {
      if (selected && grayed) {
        OS.gtk_toggle_button_set_inconsistent(handle, true);
      } else {
        OS.gtk_toggle_button_set_inconsistent(handle, false);
      }
    }
    if ((style & SWT.RADIO) != 0) {
      OS.gtk_toggle_button_set_active(groupHandle, !selected);
    }
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CLICKED);
  }
}
