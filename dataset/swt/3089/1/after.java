class PlaceHold {
  public NSString fontName() {
    long result = OS.objc_msgSend(this.id, sel_fontName);
    return result != 0 ? new NSString(result) : null;
  }
}
