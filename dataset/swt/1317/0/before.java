class PlaceHold {
  void setGCClipping() {
    int rid = data.rid;
    int widget = data.widget;
    if (rid == OS.Ph_DEV_RID) {
      OS.PgSetRegion(rid);
    } else if (widget != 0) {
      OS.PgSetRegion(OS.PtWidgetRid(widget));
    } else if (data.image != null) {
      return;
    }
    OS.PgSetMultiClip(data.clipRectsCount, data.clipRects);
    if (widget == 0) {
      return;
    }
    int clip_tile = getClipping(widget, data.topWidget, true, true, null);
    int[] clip_rects_count = new int[1];
    int clip_rects = OS.PhTilesToRects(clip_tile, clip_rects_count);
    OS.PhFreeTiles(clip_tile);
    if (clip_rects_count[0] == 0) {
      clip_rects_count[0] = 1;
      OS.free(clip_rects);
      clip_rects = OS.malloc(sizeof);
    }
    OS.PgSetClipping(((short) (clip_rects_count[0])), clip_rects);
    OS.free(clip_rects);
  }
}
