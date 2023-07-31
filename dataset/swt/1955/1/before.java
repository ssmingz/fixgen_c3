class PlaceHold {
  public NSColorSpace colorSpace() {
    int result = OS.objc_msgSend(this.id, sel_colorSpace);
    return result != 0 ? new NSColorSpace(result) : null;
  }
}
