class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    NSAutoreleasePool pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    try {
      NSRect rect = printInfo.imageablePageBounds();
      Point dpi = getDPI();
      Point screenDPI = getIndependentDPI();
      return new Rectangle(
          0,
          0,
          ((int) ((rect.width * dpi.x) / screenDPI.x)),
          ((int) ((rect.height * dpi.y) / screenDPI.y)));
    } finally {
      pool.release();
    }
  }
}
