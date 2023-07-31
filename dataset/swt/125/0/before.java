class PlaceHold {
  public id initWithLocaleIdentifier(NSString string) {
    int result =
        OS.objc_msgSend(this.id, sel_initWithLocaleIdentifier_, string != null ? string.id : 0);
    return result != 0 ? new id(result) : null;
  }
}
