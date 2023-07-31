class PlaceHold {
  public void replaceCharactersInRange(NSRange range, NSString str) {
    OS.objc_msgSend(
        this.id, sel_replaceCharactersInRange_withString_, range, str != null ? str.id : 0);
  }
}
