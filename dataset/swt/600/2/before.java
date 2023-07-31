class PlaceHold {
  public void setTextLimit(int limit) {
    checkWidget();
    if (limit == 0) {
      error(ERROR_CANNOT_BE_ZERO);
    }
    this.textLimit = ((short) (limit));
    GtkCombo combo = new GtkCombo();
    OS.memmove(combo, handle, sizeof);
    OS.gtk_entry_set_max_length(combo.entry, ((short) (limit)));
  }
}
