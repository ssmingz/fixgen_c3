class PlaceHold {
  public Rectangle getBounds() {
    checkDevice();
    NSSize size = printInfo.paperSize();
    Point dpi = getDPI();
    Point screenDPI = getIndependentDPI();
    return new Rectangle(
        0,
        0,
        ((int) ((size.width * dpi.x) / screenDPI.x)),
        ((int) ((size.height * dpi.y) / screenDPI.y)));
  }
}
