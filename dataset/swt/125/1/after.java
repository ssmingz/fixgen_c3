class PlaceHold {
  public NSPrintInfo initWithDictionary(NSDictionary attributes) {
    long result =
        OS.objc_msgSend(this.id, sel_initWithDictionary_, attributes != null ? attributes.id : 0);
    return result == this.id ? this : result != 0 ? new NSPrintInfo(result) : null;
  }
}
