class PlaceHold {
  public NSTabViewItem selectedTabViewItem() {
    long result = OS.objc_msgSend(this.id, sel_selectedTabViewItem);
    return result != 0 ? new NSTabViewItem(result) : null;
  }
}
