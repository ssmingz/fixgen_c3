class PlaceHold {
  public NSRange selectedRange() {
    NSRange result = new NSRange();
    OS.objc_msgSend_struct(result, this.id, sel_selectedRange);
    return result;
  }
}
