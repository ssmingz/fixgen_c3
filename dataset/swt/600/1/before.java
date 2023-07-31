class PlaceHold {
  void setHandleStyle() {
    GtkCombo combo = new GtkCombo();
    OS.memmove(combo, handle, sizeof);
    boolean isEditable = (style & SWT.READ_ONLY) == 0;
    OS.gtk_entry_set_editable(combo.entry, isEditable);
  }
}
