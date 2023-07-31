class PlaceHold {
  public void copyArea(int x, int y, int width, int height, int destX, int destY) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((width == 0) || (height == 0)) {
      return;
    }
    int deltaX = destX - x;
    int deltaY = destY - y;
    if ((deltaX == 0) && (deltaY == 0)) {
      return;
    }
    int flags = OS.PtEnter(0);
    try {
      boolean overlaps =
          (((destX < (x + width)) && (destY < (y + height))) && ((destX + width) > x))
              && ((destY + height) > y);
      int widget = data.widget;
      Image image = data.image;
      if (image != null) {
        int drawImage = image.handle;
        PhImage_t phDrawImage = new PhImage_t();
        OS.memmove(phDrawImage, drawImage, sizeof);
        if (overlaps) {
          PhPoint_t trans = new PhPoint_t();
          PhDim_t scale = new PhDim_t();
          scale.w = ((short) (width));
          scale.h = ((short) (height));
          PhPoint_t pos = new PhPoint_t();
          pos.x = ((short) (-x));
          pos.y = ((short) (-y));
          PhDim_t dim = new PhDim_t();
          dim.w = ((short) (Math.min(phDrawImage.size_w, x + width)));
          dim.h = ((short) (Math.min(phDrawImage.size_h, y + height)));
          int type = OS.Pg_IMAGE_PALETTE_BYTE;
          if ((phDrawImage.type & OS.Pg_IMAGE_CLASS_MASK) == OS.Pg_IMAGE_CLASS_DIRECT) {
            type = OS.Pg_IMAGE_DIRECT_888;
          }
          int memImage =
              OS.PhCreateImage(
                  null,
                  ((short) (width)),
                  ((short) (height)),
                  type,
                  phDrawImage.palette,
                  phDrawImage.colors,
                  0);
          int mc = OS.PmMemCreateMC(memImage, scale, trans);
          int prevContext = OS.PmMemStart(mc);
          OS.PgSetDrawBufferSize(DrawBufferSize);
          if (phDrawImage.palette != 0) {
            OS.PgSetPalette(
                phDrawImage.palette,
                0,
                ((short) (0)),
                ((short) (phDrawImage.colors)),
                Pg_PALSET_SOFT,
                0);
          }
          OS.PgDrawImage(phDrawImage.image, phDrawImage.type, pos, dim, phDrawImage.bpl, 0);
          if (phDrawImage.palette != 0) {
            OS.PgSetPalette(0, 0, ((short) (0)), ((short) (-1)), 0, 0);
          }
          OS.PmMemFlush(mc, memImage);
          OS.PmMemStop(mc);
          OS.PmMemReleaseMC(mc);
          OS.PhDCSetCurrent(prevContext);
          x = ((short) (0));
          y = ((short) (0));
          drawImage = memImage;
          OS.memmove(phDrawImage, drawImage, sizeof);
          phDrawImage.flags = OS.Ph_RELEASE_IMAGE_ALL;
          OS.memmove(drawImage, phDrawImage, sizeof);
        }
        PhPoint_t pos = new PhPoint_t();
        pos.x = ((short) (destX - x));
        pos.y = ((short) (destY - y));
        PhRect_t clip = new PhRect_t();
        clip.ul_x = ((short) (destX));
        clip.ul_y = ((short) (destY));
        clip.lr_x = ((short) ((destX + width) - 1));
        clip.lr_y = ((short) ((destY + height) - 1));
        PhDim_t dim = new PhDim_t();
        dim.w = ((short) (Math.min(phDrawImage.size_w, x + width)));
        dim.h = ((short) (Math.min(phDrawImage.size_h, y + height)));
        int prevContext = setGC();
        setGCClipping();
        OS.PgSetUserClip(clip);
        if (phDrawImage.palette != 0) {
          OS.PgSetPalette(
              phDrawImage.palette,
              0,
              ((short) (0)),
              ((short) (phDrawImage.colors)),
              Pg_PALSET_SOFT,
              0);
        }
        OS.PgDrawImage(phDrawImage.image, phDrawImage.type, pos, dim, phDrawImage.bpl, 0);
        if (phDrawImage.palette != 0) {
          OS.PgSetPalette(0, 0, ((short) (0)), ((short) (-1)), 0, 0);
        }
        OS.PgSetUserClip(null);
        unsetGC(prevContext);
        if (drawImage != image.handle) {
          OS.PhReleaseImage(drawImage);
          OS.free(drawImage);
        }
      } else if (widget != 0) {
        PhRect_t rect = new PhRect_t();
        rect.ul_x = ((short) (x));
        rect.ul_y = ((short) (y));
        rect.lr_x = ((short) ((x + width) - 1));
        rect.lr_y = ((short) ((y + height) - 1));
        PhPoint_t delta = new PhPoint_t();
        delta.x = ((short) (deltaX));
        delta.y = ((short) (deltaY));
        int clipRects = data.clipRects;
        int child_clip = getClipping(widget, data.topWidget, true, true, null);
        if ((clipRects == 0) && (child_clip == 0)) {
          OS.PtBlit(widget, rect, delta);
        } else {
          int srcTile = OS.PhGetTile();
          OS.memmove(srcTile, rect, sizeof);
          int clip = child_clip;
          if (clipRects != 0) {
            clip = OS.PhRectsToTiles(clipRects, data.clipRectsCount);
            if (child_clip != 0) {
              short[] unused = new short[1];
              int newClip = OS.PhIntersectTilings(clip, child_clip, unused);
              OS.PhFreeTiles(child_clip);
              OS.PhFreeTiles(clip);
              clip = newClip;
            }
          }
          OS.PtClippedBlit(widget, srcTile, delta, clip);
          OS.PhFreeTiles(clip);
        }
      }
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}
