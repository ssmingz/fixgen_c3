class PlaceHold {
  void drawImageMask(
      Image srcImage,
      int srcX,
      int srcY,
      int srcWidth,
      int srcHeight,
      int destX,
      int destY,
      int destWidth,
      int destHeight,
      boolean simple,
      int imgWidth,
      int imgHeight) {
    int drawable = data.drawable;
    int colorPixmap = srcImage.pixmap;
    if (srcImage.transparentPixel != (-1)) {
      srcImage.createMask();
    }
    int maskPixmap = srcImage.mask;
    if ((srcWidth != destWidth) || (srcHeight != destHeight)) {
      int pixbuf = OS.gdk_pixbuf_new(GDK_COLORSPACE_RGB, true, 8, srcWidth, srcHeight);
      if (pixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_pixbuf_get_from_drawable(
          pixbuf, colorPixmap, colormap, srcX, srcY, 0, 0, srcWidth, srcHeight);
      int gdkImagePtr = OS.gdk_drawable_get_image(maskPixmap, 0, 0, imgWidth, imgHeight);
      int stride = OS.gdk_pixbuf_get_rowstride(pixbuf);
      int pixels = OS.gdk_pixbuf_get_pixels(pixbuf);
      if (gdkImagePtr == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      byte[] line = new byte[stride];
      for (int y = 0; y < srcWidth; y++) {
        OS.memmove(line, pixels + (y * stride), stride);
        for (int x = 0; x < srcHeight; x++) {
          if (OS.gdk_image_get_pixel(gdkImagePtr, x + srcX, y + srcY) != 0) {
            line[(x * 4) + 3] = ((byte) (0xff));
          } else {
            line[(x * 4) + 3] = 0;
          }
        }
        OS.memmove(pixels + (y * stride), line, stride);
      }
      OS.g_object_unref(gdkImagePtr);
      int scaledPixbuf =
          OS.gdk_pixbuf_scale_simple(pixbuf, destWidth, destHeight, GDK_INTERP_BILINEAR);
      OS.g_object_unref(pixbuf);
      if (scaledPixbuf == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      OS.gdk_pixbuf_render_to_drawable_alpha(
          scaledPixbuf,
          data.drawable,
          0,
          0,
          destX,
          destY,
          destWidth,
          destHeight,
          GDK_PIXBUF_ALPHA_BILEVEL,
          128,
          GDK_RGB_DITHER_NORMAL,
          0,
          0);
      OS.g_object_unref(scaledPixbuf);
    } else {
      GdkGCValues values = new GdkGCValues();
      OS.gdk_gc_get_values(handle, values);
      OS.gdk_gc_set_clip_mask(handle, maskPixmap);
      OS.gdk_gc_set_clip_origin(handle, destX - srcX, destY - srcY);
      OS.gdk_draw_drawable(
          drawable, handle, colorPixmap, srcX, srcY, destX, destY, srcWidth, srcHeight);
      OS.gdk_gc_set_values(
          handle,
          values,
          (OS.GDK_GC_CLIP_MASK | OS.GDK_GC_CLIP_X_ORIGIN) | OS.GDK_GC_CLIP_Y_ORIGIN);
    }
    if ((colorPixmap != 0) && (srcImage.pixmap != colorPixmap)) {
      OS.g_object_unref(colorPixmap);
    }
    if ((maskPixmap != 0) && (srcImage.mask != maskPixmap)) {
      OS.g_object_unref(maskPixmap);
    }
    if ((srcImage.transparentPixel != (-1)) && (srcImage.memGC != null)) {
      srcImage.destroyMask();
    }
  }
}
