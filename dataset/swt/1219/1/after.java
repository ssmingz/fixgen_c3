class PlaceHold {
  public NSMutableDictionary dictionary() {
    long result = OS.objc_msgSend(this.id, sel_dictionary);
    return result != 0 ? new NSMutableDictionary(result) : null;
  }
}
