class PlaceHold {
  public NSData dataForType(NSString dataType) {
    int result = OS.objc_msgSend(this.id, sel_dataForType_, dataType != null ? dataType.id : 0);
    return result != 0 ? new NSData(result) : null;
  }
}
