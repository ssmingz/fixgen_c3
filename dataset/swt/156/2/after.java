class PlaceHold {
  public NSEvent currentEvent() {
    long result = OS.objc_msgSend(this.id, sel_currentEvent);
    return result != 0 ? new NSEvent(result) : null;
  }
}
