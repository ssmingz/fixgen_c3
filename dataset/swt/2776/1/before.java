class PlaceHold {
  public NSMenu menu() {
    int result = OS.objc_msgSend(this.id, sel_menu);
    return result != 0 ? new NSMenu(result) : null;
  }
}
