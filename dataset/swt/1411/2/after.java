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
      NSBitmapImageRep nativeRep = null;
      NSImageRep bestRep = nativeImage.bestRepresentationForDevice(null);
      if (!bestRep.isKindOfClass(class_NSBitmapImageRep)) {
        NSArray reps = nativeImage.representations();
        if (reps != null) {
          int repCount = reps.count();
          for (int i = 0; i < repCount; i++) {
            NSImageRep currentRep = new NSImageRep(reps.objectAtIndex(i).id);
            if (currentRep.isKindOfClass(class_NSBitmapImageRep)) {
              nativeRep = new NSBitmapImageRep(currentRep.id);
              break;
            }
          }
        }
        if (nativeRep == null) {
          nativeRep =
              new NSBitmapImageRep(
                  NSBitmapImageRep.imageRepWithData(nativeImage.TIFFRepresentation()).id);
        }
      } else {
        nativeRep = new NSBitmapImageRep(bestRep.id);
      }
      int bpp = nativeRep.bitsPerPixel();
      if (bpp > 32) {
        return;
      }
      NSString colorSpace = nativeRep.colorSpaceName();
      if (!colorSpace.isEqualToString(NSCalibratedRGBColorSpace)) {
        SWT.error(ERROR_INVALID_IMAGE);
      }
      int height = ((int) (nativeRep.pixelsHigh()));
      int width = ((int) (nativeRep.pixelsWide()));
      NSSize size = new NSSize();
      size.height = height;
      size.width = width;
      nativeImage.setSize(size);
      int bpr = nativeRep.bytesPerRow();
      int dataSize = height * bpr;
      byte imageData[] = new byte[((int) (dataSize))];
      if (!nativeRep.isPlanar()) {
        OS.memmove(imageData, nativeRep.bitmapData(), dataSize);
      } else {
        int bytesPerPlane = nativeRep.bytesPerPlane();
        int numPlanes = nativeRep.numberOfPlanes();
        int nativeDataPlanes[] = new int[5];
        nativeRep.getBitmapDataPlanes(nativeDataPlanes);
        byte dataPlanes[][] = new byte[((int) (numPlanes))][((int) (bytesPerPlane))];
        for (int i = 0; i < numPlanes; i++) {
          OS.memmove(dataPlanes[i], nativeDataPlanes[i], bytesPerPlane);
        }
        int colorComponents = OS.NSNumberOfColorComponents(colorSpace.id);
        boolean hasAlpha = false;
        if (colorComponents == 3) {
          if (numPlanes == 4) {
            hasAlpha = true;
          }
          int samplesPerPixel = (hasAlpha) ? 4 : 3;
          int firstByteOfPixel = (hasAlpha) ? 1 : 0;
          for (int j = 0, k = 0; j < bytesPerPlane; j += samplesPerPixel, k++) {
            imageData[j + firstByteOfPixel] = dataPlanes[0][k];
            imageData[(j + firstByteOfPixel) + 1] = dataPlanes[1][k];
            imageData[(j + firstByteOfPixel) + 2] = dataPlanes[2][k];
            if (hasAlpha) {
              imageData[j] = dataPlanes[3][k];
            }
          }
        }
      }
      byte[] nativeAlphaData = null;
      int nativeTransparentColor = -1;
      if (nativeRep.hasAlpha()) {
        int bitmapBytesPerRow = width;
        int bitmapByteCount = bitmapBytesPerRow * height;
        int alphaBitmapData = OS.malloc(bitmapByteCount);
        int alphaBitmapCtx =
            OS.CGBitmapContextCreate(
                alphaBitmapData, width, height, 8, bitmapBytesPerRow, 0, kCGImageAlphaOnly);
        NSGraphicsContext.static_saveGraphicsState();
        NSGraphicsContext.setCurrentContext(
            NSGraphicsContext.graphicsContextWithGraphicsPort(alphaBitmapCtx, false));
        NSRect imageRect = new NSRect();
        imageRect.x = imageRect.y = 0;
        imageRect.width = nativeImage.size().width;
        imageRect.height = nativeImage.size().height;
        NSRect zeroRect = new NSRect();
        zeroRect.x = zeroRect.y = zeroRect.height = zeroRect.width = 0;
        nativeImage.drawInRect(imageRect, zeroRect, NSCompositeCopy, 1.0F);
        NSGraphicsContext.static_restoreGraphicsState();
        nativeAlphaData = new byte[((int) (bitmapByteCount))];
        OS.memmove(nativeAlphaData, alphaBitmapData, bitmapByteCount);
        OS.free(alphaBitmapData);
        OS.CGContextRelease(alphaBitmapCtx);
        boolean hasTransparentPixel = true;
        int transparentColor = -1;
        int alphaOffset = 0;
        transparentScan:
        {
          for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
              if ((nativeAlphaData[alphaOffset] != 0) && (nativeAlphaData[alphaOffset] != (-1))) {
                hasTransparentPixel = false;
                break transparentScan;
              }
              if (nativeAlphaData[alphaOffset] == 0) {
                NSColor color = nativeRep.colorAtX(x, y);
                int red = ((int) (color.redComponent() * 255));
                int green = ((int) (color.greenComponent() * 255));
                int blue = ((int) (color.blueComponent() * 255));
                transparentColor = ((red << 16) + (green << 8)) + blue;
              }
              alphaOffset += 1;
            }
          }
        }
        if (hasTransparentPixel) {
          nativeTransparentColor = transparentColor;
        }
      }
      int dataFormat = nativeRep.bitmapFormat();
      int bps = nativeRep.bitsPerSample();
      int blueMask = (1 << bps) - 1;
      if (nativeRep.hasAlpha() && ((dataFormat & OS.NSAlphaFirstBitmapFormat) == 0)) {
        blueMask <<= bps;
      }
      int greenMask = blueMask << bps;
      int redMask = greenMask << bps;
      PaletteData palette = new PaletteData(redMask, greenMask, blueMask);
      ImageData data =
          new ImageData(((int) (width)), ((int) (height)), ((int) (bpp)), palette, 4, imageData);
      data.bytesPerLine = ((int) (bpr));
      if (nativeTransparentColor != (-1)) {
        if ((dataFormat & OS.NSAlphaFirstBitmapFormat) == 0) {
          nativeTransparentColor <<= bps;
        }
      }
      data.transparentPixel = nativeTransparentColor;
      data.alpha = -1;
      data.alphaData = nativeAlphaData;
      if (filename.toLowerCase().endsWith(".ico")) {
        this.type = SWT.ICON;
      } else {
        this.type = SWT.BITMAP;
      }
      this.init(data);
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
