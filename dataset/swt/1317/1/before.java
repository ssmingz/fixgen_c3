class PlaceHold {
  void drawWidget(int widget, int damage) {
    if ((state & CANVAS) != 0) {
      if ((style & SWT.NO_BACKGROUND) == 0) {
        int clip_tile = getClipping(handle, topHandle(), true, true);
        if (clip_tile == 0) {
          return;
        }
        short[] abs_x = new short[1];
        short[] abs_y = new short[1];
        OS.PtGetAbsPosition(handle, abs_x, abs_y);
        short[] dis_abs_x = new short[1];
        short[] dis_abs_y = new short[1];
        OS.PtGetAbsPosition(OS.PtFindDisjoint(handle), dis_abs_x, dis_abs_y);
        PhPoint_t delta = new PhPoint_t();
        delta.x = ((short) (abs_x[0] - dis_abs_x[0]));
        delta.y = ((short) (abs_y[0] - dis_abs_y[0]));
        OS.PhTranslateTiles(clip_tile, delta);
        int[] clip_rects_count = new int[1];
        int clip_rects = OS.PhTilesToRects(clip_tile, clip_rects_count);
        OS.PhFreeTiles(clip_tile);
        if (clip_rects_count[0] == 0) {
          clip_rects_count[0] = 1;
          OS.free(clip_rects);
          clip_rects = OS.malloc(sizeof);
        }
        OS.PgSetMultiClip(clip_rects_count[0], clip_rects);
        OS.free(clip_rects);
        super.drawWidget(widget, damage);
        OS.PgSetMultiClip(0, 0);
      }
    } else {
      super.drawWidget(widget, damage);
    }
  }
}
