class PlaceHold {
  public NSGraphicsContext context() {
    long result = OS.objc_msgSend(this.id, sel_context);
    return result != 0 ? new NSGraphicsContext(result) : null;
  }
}
