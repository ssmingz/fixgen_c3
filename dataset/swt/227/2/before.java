class PlaceHold {
  public void copyArea(Image image, int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (image == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if ((image.type != SWT.BITMAP) || image.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int flags = OS.PtEnter(0);
    try {
      Rectangle bounds = image.getBounds();
      int memImage = 0;
      PhRect_t rect = new PhRect_t();
      rect.ul_x = ((short) (x));
      rect.ul_y = ((short) (y));
      rect.lr_x = ((short) ((x + bounds.width) - 1));
      rect.lr_y = ((short) ((y + bounds.height) - 1));
      boolean sharedMem = true;
      int rid = data.rid;
      int widget = data.widget;
      if (rid == OS.Ph_DEV_RID) {
        memImage = OS.PgShmemCreate(OS.PgReadScreenSize(rect), null);
        if (memImage != 0) {
          memImage = OS.PgReadScreen(rect, memImage);
        }
      } else if (widget != 0) {
        short[] widget_x = new short[1];
        short[] widget_y = new short[1];
        OS.PtGetAbsPosition(widget, widget_x, widget_y);
        rect.ul_x += widget_x[0];
        rect.ul_y += widget_y[0];
        rect.lr_x += widget_y[0];
        rect.lr_y += widget_y[0];
        memImage = OS.PgShmemCreate(OS.PgReadScreenSize(rect), null);
        if (memImage != 0) {
          memImage = OS.PgReadScreen(rect, memImage);
        }
      } else if (data.image != null) {
        memImage = OS.PiCropImage(data.image.handle, rect, 0);
        sharedMem = false;
      }
      if (memImage == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      PhImage_t phImage = new PhImage_t();
      OS.memmove(phImage, memImage, sizeof);
      PhPoint_t trans = new PhPoint_t();
      PhPoint_t pos = new PhPoint_t();
      PhDim_t scale = new PhDim_t();
      scale.w = ((short) (bounds.width));
      scale.h = ((short) (bounds.height));
      int mc = OS.PmMemCreateMC(image.handle, scale, trans);
      OS.PmMemStart(mc);
      OS.PgSetDrawBufferSize(DrawBufferSize);
      if (phImage.palette != 0) {
        OS.PgSetPalette(
            phImage.palette, 0, ((short) (0)), ((short) (phImage.colors)), Pg_PALSET_SOFT, 0);
      }
      OS.PgDrawImage(phImage.image, phImage.type, pos, scale, phImage.bpl, 0);
      if (phImage.palette != 0) {
        OS.PgSetPalette(0, 0, ((short) (0)), ((short) (-1)), 0, 0);
      }
      OS.PmMemFlush(mc, image.handle);
      OS.PmMemStop(mc);
      OS.PmMemReleaseMC(mc);
      if (sharedMem) {
        OS.PgShmemDestroy(memImage);
      } else {
        phImage.flags = OS.Ph_RELEASE_IMAGE_ALL;
        OS.memmove(memImage, phImage, sizeof);
        OS.PhReleaseImage(memImage);
        OS.free(memImage);
      }
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}
