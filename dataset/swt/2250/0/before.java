class PlaceHold {
  public void copy() {
    checkWidget();
    OS.gtk_editable_copy_clipboard(entryHandle);
  }
}
