class PlaceHold {
  public NSMutableDictionary threadDictionary() {
    long result = OS.objc_msgSend(this.id, sel_threadDictionary);
    return result != 0 ? new NSMutableDictionary(result) : null;
  }
}
