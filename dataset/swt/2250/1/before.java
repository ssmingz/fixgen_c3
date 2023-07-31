class PlaceHold {
  public void cut() {
    checkWidget();
    OS.gtk_editable_cut_clipboard(entryHandle);
  }
}
