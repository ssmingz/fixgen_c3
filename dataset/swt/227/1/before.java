class PlaceHold {
  void init(Drawable drawable, GCData data, int context) {
    if (data.foreground == (-1)) {
      data.foreground = DefaultFore;
    }
    if (data.background == (-1)) {
      data.background = DefaultBack;
    }
    if (data.font == null) {
      data.font = Font.DefaultFont;
    }
    dirtyBits = (DIRTY_FOREGROUND | DIRTY_BACKGROUND) | DIRTY_FONT;
    Image image = data.image;
    if (image != null) {
      image.memGC = this;
      OS.PmMemStart(context);
      OS.PgSetDrawBufferSize(DrawBufferSize);
      OS.PmMemStop(context);
      if (image.transparentPixel != (-1)) {
        PhImage_t phImage = new PhImage_t();
        OS.memmove(phImage, image.handle, sizeof);
        if (phImage.mask_bm != 0) {
          OS.free(phImage.mask_bm);
          phImage.mask_bm = 0;
          phImage.mask_bpl = 0;
          OS.memmove(image.handle, phImage, sizeof);
        }
      }
    }
    this.drawable = drawable;
    this.data = data;
    handle = context;
  }
}
