class PlaceHold {
  void drawImage(
      Image image,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      int destX,
      int destY,
      int destWidth,
      int destHeight,
      boolean simple) {
    int flags = OS.PtEnter(0);
    try {
      if (image.memGC != null) {
        image.memGC.flushImage();
      }
      int drawImage = image.handle;
      PhImage_t phDrawImage = new PhImage_t();
      OS.memmove(phDrawImage, drawImage, sizeof);
      int imgWidth = phDrawImage.size_w;
      int imgHeight = phDrawImage.size_h;
      if (simple) {
        srcWidth = destWidth = imgWidth;
        srcHeight = destHeight = imgHeight;
      } else {
        simple =
            (((((srcX == 0) && (srcY == 0)) && (srcWidth == destWidth)) && (destWidth == imgWidth))
                    && (srcHeight == destHeight))
                && (destHeight == imgHeight);
        if (((srcX + srcWidth) > imgWidth) || ((srcY + srcHeight) > imgHeight)) {
          SWT.error(ERROR_INVALID_ARGUMENT);
        }
      }
      if ((srcWidth != destWidth) || (srcHeight != destHeight)) {
        drawImage =
            scaleImage(
                image,
                phDrawImage,
                srcX,
                srcY,
                srcWidth,
                srcHeight,
                destX,
                destY,
                destWidth,
                destHeight);
        if (drawImage == 0) {
          return;
        }
        srcX = ((short) (0));
        srcY = ((short) (0));
        srcWidth = ((short) (destWidth));
        srcHeight = ((short) (destHeight));
        OS.memmove(phDrawImage, drawImage, sizeof);
      }
      PhPoint_t pos = new PhPoint_t();
      pos.x = ((short) (destX - srcX));
      pos.y = ((short) (destY - srcY));
      PhDim_t dim = new PhDim_t();
      dim.w = ((short) (Math.min(phDrawImage.size_w, srcX + srcWidth)));
      dim.h = ((short) (Math.min(phDrawImage.size_h, srcY + srcHeight)));
      PhRect_t clip = new PhRect_t();
      clip.ul_x = ((short) (destX));
      clip.ul_y = ((short) (destY));
      clip.lr_x = ((short) ((destX + destWidth) - 1));
      clip.lr_y = ((short) ((destY + destHeight) - 1));
      int prevContext = setGC();
      setGCClipping();
      OS.PgSetDrawMode(data.xorMode ? OS.Pg_DrawModeDSx : OS.Pg_DrawModeS);
      dirtyBits |= DIRTY_XORMODE;
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
      if (phDrawImage.alpha != 0) {
        drawImageAlpha(
            image,
            srcX,
            srcY,
            srcWidth,
            srcHeight,
            destX,
            destY,
            destWidth,
            destHeight,
            simple,
            phDrawImage,
            drawImage,
            pos,
            dim);
      } else if (image.transparentPixel != (-1)) {
        drawImageTransparent(
            image,
            srcX,
            srcY,
            srcWidth,
            srcHeight,
            destX,
            destY,
            destWidth,
            destHeight,
            simple,
            phDrawImage,
            drawImage,
            pos,
            dim);
      } else if (phDrawImage.mask_bm != 0) {
        drawImageMask(
            image,
            srcX,
            srcY,
            srcWidth,
            srcHeight,
            destX,
            destY,
            destWidth,
            destHeight,
            simple,
            phDrawImage,
            drawImage,
            pos,
            dim);
      } else {
        drawImage(
            image,
            srcX,
            srcY,
            srcWidth,
            srcHeight,
            destX,
            destY,
            destWidth,
            destHeight,
            simple,
            phDrawImage,
            drawImage,
            pos,
            dim);
      }
      if (phDrawImage.palette != 0) {
        OS.PgSetPalette(0, 0, ((short) (0)), ((short) (-1)), 0, 0);
      }
      OS.PgSetUserClip(null);
      unsetGC(prevContext);
      if (drawImage != image.handle) {
        phDrawImage.flags = OS.Ph_RELEASE_IMAGE_ALL;
        OS.memmove(drawImage, phDrawImage, sizeof);
        OS.PhReleaseImage(drawImage);
        OS.free(drawImage);
      }
    } finally {
      if (flags >= 0) {
        OS.PtLeave(flags);
      }
    }
  }
}
