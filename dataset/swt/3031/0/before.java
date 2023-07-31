class PlaceHold {
  public NSOpenGLPixelFormat initWithAttributes(int[] attribs) {
    int result = OS.objc_msgSend(this.id, sel_initWithAttributes_1, attribs);
    return result != 0 ? this : null;
  }
}
