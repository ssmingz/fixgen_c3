class PlaceHold {
  public int declareTypes(NSArray newTypes, id newOwner) {
    return ((int)
        (OS.objc_msgSend(
            this.id,
            sel_declareTypes_owner_,
            newTypes != null ? newTypes.id : 0,
            newOwner != null ? newOwner.id : 0)));
  }
}
