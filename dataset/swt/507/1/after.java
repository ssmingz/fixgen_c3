class PlaceHold {
  public int getTextLimit() {
    checkWidget();
    int limit = OS.gtk_entry_get_max_length(entryHandle);
    return limit == 0 ? LIMIT : limit;
  }
}
