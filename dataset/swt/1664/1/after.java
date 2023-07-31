class PlaceHold {
  public id initWithIdentifier(id identifier) {
    int result =
        OS.objc_msgSend(this.id, sel_initWithIdentifier_, identifier != null ? identifier.id : 0);
    return result != 0 ? new id(result) : null;
  }
}
