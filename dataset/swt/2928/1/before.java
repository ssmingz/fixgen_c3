class PlaceHold {
  public NSString pathForResource(NSString name, NSString ext) {
    int result =
        OS.objc_msgSend(
            this.id,
            sel_pathForResource_ofType_,
            name != null ? name.id : 0,
            ext != null ? ext.id : 0);
    return result != 0 ? new NSString(result) : null;
  }
}
