class PlaceHold {
  public NSEvent currentEvent() {
    int result = OS.objc_msgSend(this.id, sel_currentEvent);
    return result != 0 ? new NSEvent(result) : null;
  }
}
