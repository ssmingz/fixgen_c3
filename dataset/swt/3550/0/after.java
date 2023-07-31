class PlaceHold {
  public NSAttributedString attributedSubstringFromRange(NSRange range) {
    long result = OS.objc_msgSend(this.id, sel_attributedSubstringFromRange_, range);
    return result == this.id ? this : result != 0 ? new NSAttributedString(result) : null;
  }
}
