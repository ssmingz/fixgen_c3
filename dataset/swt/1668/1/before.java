class PlaceHold {
  int drawProc(int widget, int damage) {
    drawWidget(widget, damage);
    if ((!hooks(Paint)) && (!filters(Paint))) {
      return OS.Pt_CONTINUE;
    }
    short[] widgetX = new short[1];
    short[] widgetY = new short[1];
    OS.PtGetAbsPosition(handle, widgetX, widgetY);
    short[] shellX = new short[1];
    short[] shellY = new short[1];
    int shellHandle = OS.PtFindDisjoint(handle);
    OS.PtGetAbsPosition(shellHandle, shellX, shellY);
    PhPoint_t pt = new PhPoint_t();
    pt.x = ((short) (shellX[0] - widgetX[0]));
    pt.y = ((short) (shellY[0] - widgetY[0]));
    damage = OS.PhCopyTiles(damage);
    damage = OS.PhTranslateTiles(damage, pt);
    PhTile_t tile = new PhTile_t();
    OS.memmove(tile, damage, sizeof);
    boolean noMerge = ((style & SWT.NO_MERGE_PAINTS) != 0) && ((state & CANVAS) != 0);
    if ((tile.next != 0) && noMerge) {
      while (tile.next != 0) {
        OS.memmove(tile, tile.next, sizeof);
        if ((tile.rect_ul_x != tile.rect_lr_x) || (tile.rect_ul_y != tile.rect_lr_y)) {
          Event event = new Event();
          event.x = tile.rect_ul_x;
          event.y = tile.rect_ul_y;
          event.width = (tile.rect_lr_x - tile.rect_ul_x) + 1;
          event.height = (tile.rect_lr_y - tile.rect_ul_y) + 1;
          GC gc = event.gc = new GC(this);
          gc.setClipping(event.x, event.y, event.width, event.height);
          sendEvent(Paint, event);
          if (isDisposed()) {
            break;
          }
          gc.dispose();
          event.gc = null;
        }
      }
    } else if ((tile.rect_ul_x != tile.rect_lr_x) || (tile.rect_ul_y != tile.rect_lr_y)) {
      Event event = new Event();
      event.x = tile.rect_ul_x;
      event.y = tile.rect_ul_y;
      event.width = (tile.rect_lr_x - tile.rect_ul_x) + 1;
      event.height = (tile.rect_lr_y - tile.rect_ul_y) + 1;
      Region region = Region.photon_new(tile.next);
      GC gc = event.gc = new GC(this);
      gc.setClipping(region);
      sendEvent(Paint, event);
      gc.dispose();
      event.gc = null;
    }
    OS.PhFreeTiles(damage);
    return OS.Pt_CONTINUE;
  }
}
