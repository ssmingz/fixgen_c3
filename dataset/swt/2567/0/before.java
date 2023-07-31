class PlaceHold {
  public int addTypes(NSArray newTypes, id newOwner) {
    return OS.objc_msgSend(
        this.id,
        sel_addTypes_owner_,
        newTypes != null ? newTypes.id : 0,
        newOwner != null ? newOwner.id : 0);
  }
}
