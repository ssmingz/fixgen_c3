class PlaceHold {
  boolean drawCaret() {
    if (parent == null) {
      return false;
    }
    if (parent.isDisposed()) {
      return false;
    }
    int parentHandle = parent.handle;
    if (!parent.isDrawing(parentHandle)) {
      return false;
    }
    int nWidth = width;
    int nHeight = height;
    if (nWidth <= 0) {
      nWidth = DEFAULT_WIDTH;
    }
    int window = OS.GetControlOwner(parentHandle);
    int port = OS.GetWindowPort(window);
    int[] currentPort = new int[1];
    OS.GetPort(currentPort);
    OS.SetPort(port);
    int oldClip = OS.NewRgn();
    int visibleRgn = parent.getVisibleRegion(parentHandle, true);
    OS.GetClip(oldClip);
    OS.SetClip(visibleRgn);
    Rect rect = new Rect();
    OS.GetControlBounds(parentHandle, rect);
    CGPoint pt = new CGPoint();
    int[] contentView = new int[1];
    OS.HIViewFindByID(OS.HIViewGetRoot(window), OS.kHIViewWindowContentID(), contentView);
    OS.HIViewConvertPoint(pt, OS.HIViewGetSuperview(parentHandle), contentView[0]);
    rect.left += ((int) (pt.x));
    rect.top += ((int) (pt.y));
    int left = rect.left + x;
    int top = rect.top + y;
    if (image == null) {
      OS.SetRect(
          rect,
          ((short) (left)),
          ((short) (top)),
          ((short) (left + nWidth)),
          ((short) (top + nHeight)));
      RGBColor color = new RGBColor();
      color.red = ((short) (0xffff));
      color.green = ((short) (0xffff));
      color.blue = ((short) (0xffff));
      OS.RGBBackColor(color);
      OS.InvertRect(rect);
    } else {
      int imageHandle = image.handle;
      nWidth = OS.CGImageGetWidth(imageHandle);
      nHeight = OS.CGImageGetHeight(imageHandle);
      int bpl = OS.CGImageGetBytesPerRow(imageHandle);
      Rect bounds = new Rect();
      bounds.right = ((short) (nWidth));
      bounds.bottom = ((short) (nHeight));
      Rect portRect = new Rect();
      OS.GetWindowBounds(window, ((short) (kWindowContentRgn)), portRect);
      left += portRect.left;
      top += portRect.top;
      OS.SetRect(
          rect,
          ((short) (left)),
          ((short) (top)),
          ((short) (left + nWidth)),
          ((short) (top + nHeight)));
      int[] gWorld = new int[1];
      OS.NewGWorldFromPtr(gWorld, k32ARGBPixelFormat, bounds, 0, 0, 0, image.data, bpl);
      int[] curPort = new int[1];
      int[] curGWorld = new int[1];
      OS.GetGWorld(curPort, curGWorld);
      OS.SetGWorld(gWorld[0], curGWorld[0]);
      int portBitMap = OS.GetPortBitMapForCopyBits(port);
      int gworldBitMap = OS.GetPortBitMapForCopyBits(gWorld[0]);
      OS.OffsetRgn(visibleRgn, portRect.left, portRect.top);
      OS.CopyBits(gworldBitMap, portBitMap, bounds, rect, ((short) (notSrcXor)), visibleRgn);
      OS.OffsetRgn(visibleRgn, ((short) (-portRect.left)), ((short) (-portRect.top)));
      OS.SetGWorld(curPort[0], curGWorld[0]);
      OS.DisposeGWorld(gWorld[0]);
    }
    OS.SetClip(oldClip);
    OS.DisposeRgn(visibleRgn);
    OS.DisposeRgn(oldClip);
    OS.SetPort(currentPort[0]);
    return true;
  }
}
