class PlaceHold {
  void initNative(String filename) {
    NSAutoreleasePool pool = null;
    NSImage nativeImage = null;
    if (!NSThread.isMainThread()) {
      pool = ((NSAutoreleasePool) (new NSAutoreleasePool().alloc().init()));
    }
    try {
      nativeImage = new NSImage();
      nativeImage.alloc();
      nativeImage = nativeImage.initWithContentsOfFile(NSString.stringWith(filename));
      if (nativeImage == null) {
        return;
      }
      NSImageRep nativeRep = nativeImage.bestRepresentationForDevice(null);
      width = ((int) (nativeRep.pixelsWide()));
      height = ((int) (nativeRep.pixelsHigh()));
      boolean hasAlpha = nativeRep.hasAlpha();
      int bpr = width * 4;
      handle = ((NSImage) (new NSImage().alloc()));
      NSSize size = new NSSize();
      size.width = width;
      size.height = height;
      handle = handle.initWithSize(size);
      NSBitmapImageRep rep = imageRep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
      rep =
          rep.initWithBitmapDataPlanes(
              0,
              width,
              height,
              8,
              hasAlpha ? 4 : 3,
              hasAlpha,
              false,
              NSDeviceRGBColorSpace,
              OS.NSAlphaFirstBitmapFormat | OS.NSAlphaNonpremultipliedBitmapFormat,
              bpr,
              32);
      handle.addRepresentation(rep);
      nativeImage.setSize(size);
      rep.setAlpha(false);
      NSGraphicsContext context = NSGraphicsContext.graphicsContextWithBitmapImageRep(rep);
      NSGraphicsContext.static_saveGraphicsState();
      NSGraphicsContext.setCurrentContext(context);
      NSRect rect = new NSRect();
      rect.width = width;
      rect.height = height;
      nativeImage.drawInRect(rect, rect, NSCompositeCopy, 1);
      NSGraphicsContext.static_restoreGraphicsState();
      rep.setAlpha(hasAlpha);
      if (hasAlpha) {
        int bitmapBytesPerRow = width;
        int bitmapByteCount = bitmapBytesPerRow * height;
        int alphaBitmapData = OS.malloc(bitmapByteCount);
        int alphaBitmapCtx =
            OS.CGBitmapContextCreate(
                alphaBitmapData, width, height, 8, bitmapBytesPerRow, 0, kCGImageAlphaOnly);
        NSGraphicsContext.static_saveGraphicsState();
        NSGraphicsContext.setCurrentContext(
            NSGraphicsContext.graphicsContextWithGraphicsPort(alphaBitmapCtx, false));
        nativeImage.drawInRect(rect, new NSRect(), NSCompositeCopy, 1.0F);
        NSGraphicsContext.static_restoreGraphicsState();
        byte[] alphaData = new byte[((int) (bitmapByteCount))];
        OS.memmove(alphaData, alphaBitmapData, bitmapByteCount);
        OS.free(alphaBitmapData);
        OS.CGContextRelease(alphaBitmapCtx);
        int transparentOffset = -1;
        int i = 0;
        for (i = 0; i < alphaData.length; i++) {
          int alpha = alphaData[i];
          if ((transparentOffset == (-1)) && (alpha == 0)) {
            transparentOffset = i;
          }
          if (!((alpha == 0) || (alpha == (-1)))) {
            break;
          }
        }
        this.alpha = -1;
        if ((i == alphaData.length) && (transparentOffset != (-1))) {
          NSColor color = rep.colorAtX(transparentOffset % width, transparentOffset / width);
          int red = ((int) (color.redComponent() * 255));
          int green = ((int) (color.greenComponent() * 255));
          int blue = ((int) (color.blueComponent() * 255));
          this.transparentPixel = ((red << 16) + (green << 8)) + blue;
        } else {
          this.alphaData = alphaData;
        }
      }
      if (filename.toLowerCase().endsWith(".ico")) {
        this.type = SWT.ICON;
      } else {
        this.type = SWT.BITMAP;
      }
    } finally {
      if (nativeImage != null) {
        nativeImage.release();
      }
      if (pool != null) {
        pool.release();
      }
    }
  }
}
