class PlaceHold {
  public void setTextLimit(int limit) {
    checkWidget();
    if (limit == 0) {
      error(ERROR_CANNOT_BE_ZERO);
    }
    this.textLimit = ((short) (limit));
    OS.gtk_entry_set_max_length(entryHandle, ((short) (limit)));
  }
}
