class PlaceHold {
  public void replaceCharactersInRange_withString_(NSRange range, NSString aString) {
    OS.objc_msgSend(
        this.id,
        sel_replaceCharactersInRange_1withString_1,
        range,
        aString != null ? aString.id : 0);
  }
}
