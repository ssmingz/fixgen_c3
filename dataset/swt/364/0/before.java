class PlaceHold {
  long imContext() {
    if ((style & SWT.SINGLE) != 0) {
      return OS.gtk_editable_get_editable(handle) ? OS.GTK_ENTRY_IM_CONTEXT(handle) : 0;
    }
    return OS.GTK_TEXTVIEW_IM_CONTEXT(handle);
  }
}
