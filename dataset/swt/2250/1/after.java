class PlaceHold {
  public void cut() {
    checkWidget();
    if (entryHandle != 0) {
      OS.gtk_editable_cut_clipboard(entryHandle);
    }
  }
}
