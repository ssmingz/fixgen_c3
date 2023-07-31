class PlaceHold {
  public NSMutableDictionary threadDictionary() {
    int result = OS.objc_msgSend(this.id, sel_threadDictionary);
    return result != 0 ? new NSMutableDictionary(result) : null;
  }
}
