class PlaceHold {
  void drawImage(
      Image srcImage,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      int destX,
      int destY,
      int destWidth,
      int destHeight,
      boolean simple,
      int imgWidth,
      int imgHeight,
      int depth) {
    int xDisplay = data.display;
    int xDrawable = data.drawable;
    if ((srcWidth == destWidth) && (srcHeight == destHeight)) {
      OS.XCopyArea(
          xDisplay,
          srcImage.pixmap,
          xDrawable,
          handle,
          srcX,
          srcY,
          srcWidth,
          srcHeight,
          destX,
          destY);
      return;
    }
    int xImagePtr =
        scalePixmap(
            xDisplay,
            srcImage.pixmap,
            srcX,
            srcY,
            srcWidth,
            srcHeight,
            destX,
            destY,
            destWidth,
            destHeight,
            false,
            false);
    if (xImagePtr != 0) {
      OS.XPutImage(
          xDisplay, xDrawable, handle, xImagePtr, 0, 0, destX, destY, destWidth, destHeight);
      OS.XDestroyImage(xImagePtr);
    }
  }
}
