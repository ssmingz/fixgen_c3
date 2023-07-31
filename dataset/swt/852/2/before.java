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
      if (!nativeRep.isKindOfClass(class_NSBitmapImageRep)) {
        return;
      }
      width = ((int) (nativeRep.pixelsWide()));
      height = ((int) (nativeRep.pixelsHigh()));
      boolean hasAlpha = nativeRep.hasAlpha();
      int bpr = width * 4;
      handle = ((NSImage) (new NSImage().alloc()));
      NSSize size = new NSSize();
      size.width = width;
      size.height = height;
      handle = handle.initWithSize(size);
      NSBitmapImageRep rep = ((NSBitmapImageRep) (new NSBitmapImageRep().alloc()));
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
      rep.release();
      NSRect rect = new NSRect();
      rect.width = width;
      rect.height = height;
      int colorspace = OS.CGColorSpaceCreateDeviceRGB();
      int ctx =
          OS.CGBitmapContextCreate(
              rep.bitmapData(), width, height, 8, bpr, colorspace, kCGImageAlphaNoneSkipFirst);
      OS.CGColorSpaceRelease(colorspace);
      NSGraphicsContext.static_saveGraphicsState();
      NSGraphicsContext.setCurrentContext(
          NSGraphicsContext.graphicsContextWithGraphicsPort(ctx, false));
      if (hasAlpha) {
        OS.objc_msgSend(nativeRep.id, sel_setAlpha_, 0);
      }
      nativeRep.drawInRect(rect);
      if (hasAlpha) {
        OS.objc_msgSend(nativeRep.id, sel_setAlpha_, 1);
      }
      NSGraphicsContext.static_restoreGraphicsState();
      OS.CGContextRelease(ctx);
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
        nativeRep.drawInRect(rect);
        NSGraphicsContext.static_restoreGraphicsState();
        byte[] alphaData = new byte[((int) (bitmapByteCount))];
        OS.memmove(alphaData, alphaBitmapData, bitmapByteCount);
        OS.free(alphaBitmapData);
        OS.CGContextRelease(alphaBitmapCtx);
        byte[] srcData = new byte[height * bpr];
        OS.memmove(srcData, rep.bitmapData(), srcData.length);
        for (int a = 0, p = 0; a < alphaData.length; a++, p += 4) {
          srcData[p] = alphaData[a];
        }
        OS.memmove(rep.bitmapData(), srcData, srcData.length);
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
