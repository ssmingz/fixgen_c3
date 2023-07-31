class PlaceHold {
  public NSMenu menu() {
    long result = OS.objc_msgSend(this.id, sel_menu);
    return result != 0 ? new NSMenu(result) : null;
  }
}
