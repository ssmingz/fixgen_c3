class PlaceHold {
  public NSString displayNameForKey(id key, id value) {
    long result =
        OS.objc_msgSend(
            this.id,
            sel_displayNameForKey_value_,
            key != null ? key.id : 0,
            value != null ? value.id : 0);
    return result != 0 ? new NSString(result) : null;
  }
}
