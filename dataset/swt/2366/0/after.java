class PlaceHold {
  public void showSelection() {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    long mark = OS.gtk_text_buffer_get_selection_bound(bufferHandle);
    OS.gtk_text_view_scroll_mark_onscreen(handle, mark);
    mark = OS.gtk_text_buffer_get_insert(bufferHandle);
    OS.gtk_text_view_scroll_mark_onscreen(handle, mark);
  }
}
