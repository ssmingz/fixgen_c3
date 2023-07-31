class PlaceHold {
  public NSTableColumn initWithIdentifier(id identifier) {
    int result =
        OS.objc_msgSend(this.id, sel_initWithIdentifier_1, identifier != null ? identifier.id : 0);
    return result != 0 ? this : null;
  }
}
