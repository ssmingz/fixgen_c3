class PlaceHold {
  public void replaceCharactersInRange_withString_(NSRange range, NSString str) {
    OS.objc_msgSend(
        this.id, sel_replaceCharactersInRange_1withString_1, range, str != null ? str.id : 0);
  }
}
