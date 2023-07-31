class PlaceHold {
  public NSSize contentViewMargins() {
    NSSize result = new NSSize();
    OS.objc_msgSend_struct(result, this.id, sel_contentViewMargins);
    return result;
  }
}
