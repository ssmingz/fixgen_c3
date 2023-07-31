class PlaceHold {
  public void drawString(String string, int x, int y, boolean isTransparent) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int drawFlags = OS.Pg_TEXT_LEFT | OS.Pg_TEXT_TOP;
    if (!isTransparent) {
      drawFlags |= OS.Pg_BACK_FILL;
    }
    byte[] buffer = Converter.wcsToMbcs(null, string, false);
    int flags = OS.PtEnter(0);
    try {
      int prevContext = setGC();
      setGCClipping();
      if (!data.xorMode) {
        OS.PgDrawText(buffer, buffer.length, ((short) (x)), ((short) (y)), drawFlags);
      } else if (isTransparent) {
        PhRect_t rect = new PhRect_t();
        OS.PfExtentText(rect, null, data.font, buffer, buffer.length);
        short width = ((short) ((rect.lr_x - rect.ul_x) + 1));
        short height = ((short) ((rect.lr_y - rect.ul_y) + 1));
        int image = OS.PhCreateImage(null, width, height, Pg_IMAGE_DIRECT_888, 0, 0, 0);
        PhDim_t dim = new PhDim_t();
        dim.w = width;
        dim.h = height;
        PhPoint_t point = new PhPoint_t();
        int pmMC = OS.PmMemCreateMC(image, dim, point);
        if (pmMC == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        int prevCont = OS.PmMemStart(pmMC);
        OS.PgSetTextColor(data.foreground);
        OS.PgSetFont(data.font);
        OS.PgDrawText(buffer, buffer.length, ((short) (0)), ((short) (0)), drawFlags);
        OS.PmMemFlush(pmMC, image);
        OS.PmMemStop(pmMC);
        OS.PhDCSetCurrent(prevCont);
        OS.PmMemReleaseMC(pmMC);
        point.x = ((short) (x));
        point.y = ((short) (y));
        PhImage_t phImage = new PhImage_t();
        OS.memmove(phImage, image, sizeof);
        OS.PgDrawImage(phImage.image, phImage.type, point, dim, phImage.bpl, 0);
        phImage.flags = OS.Ph_RELEASE_IMAGE_ALL;
        OS.memmove(image, phImage, sizeof);
        OS.PhReleaseImage(image);
        OS.free(image);
      } else {
        OS.PgSetTextXORColor(data.foreground, data.background);
        OS.PgSetDrawMode(Pg_DrawModeS);
        OS.PgDrawText(buffer, buffer.length, ((short) (x)), ((short) (y)), drawFlags);
        dirtyBits |= DIRTY_XORMODE | DIRTY_FOREGROUND;
      }
      unsetGC(prevContext);
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}
