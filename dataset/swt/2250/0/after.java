class PlaceHold {
  public void copy() {
    checkWidget();
    if (entryHandle != 0) {
      OS.gtk_editable_copy_clipboard(entryHandle);
    }
  }
}
