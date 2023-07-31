class PlaceHold {
  public ImageData getImageData() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    PhImage_t phImage = new PhImage_t();
    OS.memmove(phImage, handle, sizeof);
    int depth = 0;
    PaletteData palette = null;
    switch (phImage.type) {
      case OS.Pg_IMAGE_DIRECT_555:
        depth = 16;
        palette = new PaletteData(0x7c00, 0x3e0, 0x1f);
        break;
      case OS.Pg_IMAGE_DIRECT_565:
        depth = 16;
        palette = new PaletteData(0xf800, 0x7e0, 0x1f);
        break;
      case OS.Pg_IMAGE_DIRECT_444:
        depth = 16;
        palette = new PaletteData(0xf00, 0xf0, 0xf);
        break;
      case OS.Pg_IMAGE_DIRECT_888:
        depth = 24;
        palette = new PaletteData(0xff, 0xff00, 0xff0000);
        break;
      case OS.Pg_IMAGE_DIRECT_8888:
        depth = 32;
        palette = new PaletteData(0xff00, 0xff0000, 0xff000000);
        break;
      case -1:
        depth = 1;
        palette = new PaletteData(new RGB[] {new RGB(0, 0, 0), new RGB(255, 255, 255)});
        break;
      case OS.Pg_IMAGE_PALETTE_NIBBLE:
      case OS.Pg_IMAGE_PALETTE_BYTE:
        depth = (phImage.type == OS.Pg_IMAGE_PALETTE_BYTE) ? 8 : 4;
        RGB[] rgbs = new RGB[phImage.colors];
        int[] colors = new int[phImage.colors];
        OS.memmove(colors, phImage.palette, colors.length * 4);
        for (int i = 0; i < rgbs.length; i++) {
          int rgb = colors[i];
          rgbs[i] = new RGB((rgb & 0xff0000) >> 16, (rgb & 0xff00) >> 8, rgb & 0xff);
        }
        palette = new PaletteData(rgbs);
        break;
      default:
        SWT.error(ERROR_UNSUPPORTED_DEPTH);
    }
    int scanLinePad;
    int bpl = phImage.bpl;
    int width = phImage.size_w;
    int height = phImage.size_h;
    int dataBytesPerLine = ((width * depth) + 7) / 8;
    for (scanLinePad = 1; scanLinePad < 128; scanLinePad++) {
      int calcBpl = ((dataBytesPerLine + (scanLinePad - 1)) / scanLinePad) * scanLinePad;
      if (bpl == calcBpl) {
        break;
      }
    }
    byte[] data = new byte[height * bpl];
    OS.memmove(data, phImage.image, data.length);
    ImageData imageData = new ImageData(width, height, depth, palette, scanLinePad, data);
    if (transparentPixel != (-1)) {
      imageData.transparentPixel = transparentPixel;
    } else if (phImage.mask_bm != 0) {
      imageData.maskData = new byte[height * phImage.mask_bpl];
      OS.memmove(imageData.maskData, phImage.mask_bm, imageData.maskData.length);
      imageData.maskPad = 2;
    } else if (phImage.alpha != 0) {
      PgAlpha_t alpha = new PgAlpha_t();
      OS.memmove(alpha, phImage.alpha, sizeof);
      imageData.alpha = alpha.src_global_alpha;
      if (((alpha.alpha_op & OS.Pg_ALPHA_OP_SRC_MAP) != 0) && (alpha.src_alpha_map_map != 0)) {
        int length = alpha.src_alpha_map_dim_w * alpha.src_alpha_map_dim_h;
        imageData.alphaData = new byte[length];
        OS.memmove(imageData.alphaData, alpha.src_alpha_map_map, length);
      }
    }
    return imageData;
  }
}
