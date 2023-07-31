class PlaceHold {
  public NSGraphicsContext context() {
    int result = OS.objc_msgSend(this.id, sel_context);
    return result != 0 ? new NSGraphicsContext(result) : null;
  }
}
