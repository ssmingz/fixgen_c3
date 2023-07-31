class PlaceHold {
  public NSMenu mainMenu() {
    long result = OS.objc_msgSend(this.id, sel_mainMenu);
    return result != 0 ? new NSMenu(result) : null;
  }
}
