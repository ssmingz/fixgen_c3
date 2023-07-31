class PlaceHold {
  public static NSBundle bundleWithPath(NSString path) {
    int result = OS.objc_msgSend(class_NSBundle, sel_bundleWithPath_, path != null ? path.id : 0);
    return result != 0 ? new NSBundle(result) : null;
  }
}
