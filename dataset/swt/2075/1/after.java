class PlaceHold {
  public NSAttributedString attributedTitle() {
    long result = OS.objc_msgSend(this.id, sel_attributedTitle);
    return result != 0 ? new NSAttributedString(result) : null;
  }
}
