class PlaceHold {
  public void setClipping(Region region) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int clipRects = data.clipRects;
    int clipRectsCount = data.clipRectsCount;
    if (clipRects != 0) {
      OS.free(clipRects);
    }
    if ((region == null) || (region.handle == 0)) {
      clipRects = clipRectsCount = 0;
    } else if (region.handle == Region.EMPTY_REGION) {
      clipRects = OS.malloc(sizeof);
      OS.memset(clipRects, 0, sizeof);
      clipRectsCount = 1;
    } else {
      int[] clip_rects_count = new int[1];
      clipRects = OS.PhTilesToRects(region.handle, clip_rects_count);
      clipRectsCount = clip_rects_count[0];
    }
    data.clipRects = clipRects;
    data.clipRectsCount = clipRectsCount;
    dirtyBits |= DIRTY_CLIPPING;
  }
}
