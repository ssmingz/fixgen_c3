class PlaceHold {
  public NSMutableDictionary dictionary() {
    int result = OS.objc_msgSend(this.id, sel_dictionary);
    return result != 0 ? new NSMutableDictionary(result) : null;
  }
}
