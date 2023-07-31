class PlaceHold {
  public NSAttributedString attributedStringValue() {
    long result = OS.objc_msgSend(this.id, sel_attributedStringValue);
    return result != 0 ? new NSAttributedString(result) : null;
  }
}
