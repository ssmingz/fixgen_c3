class PlaceHold {
  public NSString stringForType(NSString dataType) {
    long result = OS.objc_msgSend(this.id, sel_stringForType_, dataType != null ? dataType.id : 0);
    return result != 0 ? new NSString(result) : null;
  }
}
