class PlaceHold {
  public static NSURL fileURLWithPath(NSString path) {
    int result = OS.objc_msgSend(class_NSURL, sel_fileURLWithPath_, path != null ? path.id : 0);
    return result != 0 ? new NSURL(result) : null;
  }
}
