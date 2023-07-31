class PlaceHold {
  public void dispose() {
    int flags = OS.PtEnter(0);
    try {
      if (handle == 0) {
        return;
      }
      if (data.device.isDisposed()) {
        return;
      }
      int clipRects = data.clipRects;
      if (clipRects != 0) {
        OS.free(clipRects);
        data.clipRects = data.clipRectsCount = 0;
      }
      Image image = data.image;
      if (image != null) {
        flushImage();
        if (image.transparentPixel != (-1)) {
          PhImage_t phImage = new PhImage_t();
          OS.memmove(phImage, image.handle, sizeof);
          if (phImage.mask_bm == 0) {
            createMask(image.handle, phImage.type, image.transparentPixel);
          }
        }
        image.memGC = null;
      }
      Device device = data.device;
      drawable.internal_dispose_GC(handle, data);
      drawable = null;
      handle = 0;
      data.image = null;
      data.font = null;
      data.rid = data.widget = data.topWidget = 0;
      if (device.tracking) {
        device.dispose_Object(this);
      }
      data.device = null;
      data = null;
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}
