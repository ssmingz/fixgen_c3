class PlaceHold {
  int gtk_icon_release(int widget, int icon_pos, int event) {
    Event e = new Event();
    if (icon_pos == OS.GTK_ENTRY_ICON_PRIMARY) {
      e.detail = SWT.ICON_SEARCH;
    } else {
      e.detail = SWT.ICON_CANCEL;
      OS.gtk_editable_delete_text(handle, 0, -1);
    }
    postEvent(DefaultSelection, e);
    return 0;
  }
}
