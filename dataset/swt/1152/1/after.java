class PlaceHold {
  public void replaceCharactersInRange(NSRange range, NSString aString) {
    OS.objc_msgSend(
        this.id, sel_replaceCharactersInRange_withString_, range, aString != null ? aString.id : 0);
  }
}
