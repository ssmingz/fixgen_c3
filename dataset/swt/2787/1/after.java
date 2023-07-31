class PlaceHold {
  void destroy() {
    if (data.disposeCairo) {
      long cairo = data.cairo;
      if (cairo != 0) {
        Cairo.cairo_destroy(cairo);
      }
    }
    data.cairo = 0;
    long clipRgn = data.clipRgn;
    if (clipRgn != 0) {
      Region.cairo_region_destroy(clipRgn);
    }
    Image image = data.image;
    if (image != null) {
      image.memGC = null;
      if (image.transparentPixel != (-1)) {
        image.createMask();
      }
    }
    disposeLayout();
    if (drawable != null) {
      drawable.internal_dispose_GC(handle, data);
    }
    data.drawable = data.clipRgn = 0;
    drawable = null;
    handle = 0;
    data.image = null;
    data.string = null;
    data = null;
  }
}
