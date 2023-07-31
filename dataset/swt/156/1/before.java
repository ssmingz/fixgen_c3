class PlaceHold {
  public NSText currentEditor() {
    int result = OS.objc_msgSend(this.id, sel_currentEditor);
    return result != 0 ? new NSText(result) : null;
  }
}
