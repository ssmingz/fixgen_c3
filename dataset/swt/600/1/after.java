class PlaceHold {
  void setHandleStyle() {
    boolean isEditable = (style & SWT.READ_ONLY) == 0;
    OS.gtk_entry_set_editable(entryHandle, isEditable);
  }
}
