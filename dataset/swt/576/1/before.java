class PlaceHold {
  void init(Device device, ImageData i) {
    if (i == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    if ((((i.depth == 1) || (i.depth == 2)) || (i.depth == 4)) && (!i.palette.isDirect)) {
      ImageData img = new ImageData(i.width, i.height, 8, i.palette);
      ImageData.blit(
          BLIT_SRC,
          i.data,
          i.depth,
          i.bytesPerLine,
          img.getByteOrder(),
          0,
          0,
          i.width,
          i.height,
          null,
          null,
          null,
          ALPHA_OPAQUE,
          null,
          0,
          0,
          0,
          img.data,
          img.depth,
          img.bytesPerLine,
          img.getByteOrder(),
          0,
          0,
          img.width,
          img.height,
          null,
          null,
          null,
          false,
          false);
      img.transparentPixel = i.transparentPixel;
      img.maskPad = i.maskPad;
      img.maskData = i.maskData;
      img.alpha = i.alpha;
      img.alphaData = i.alphaData;
      i = img;
    }
    int type = 0;
    int[] phPalette = null;
    if (!i.palette.isDirect) {
      switch (i.depth) {
        case 4:
          type = OS.Pg_IMAGE_PALETTE_NIBBLE;
          break;
        case 8:
          type = OS.Pg_IMAGE_PALETTE_BYTE;
          break;
        default:
          SWT.error(ERROR_UNSUPPORTED_DEPTH);
      }
      RGB[] rgbs = i.palette.getRGBs();
      phPalette = new int[rgbs.length];
      for (int j = 0; j < rgbs.length; j++) {
        RGB rgb = rgbs[j];
        phPalette[j] = (((rgb.red & 0xff) << 16) | ((rgb.green & 0xff) << 8)) | (rgb.blue & 0xff);
      }
    } else {
      final PaletteData palette = i.palette;
      final int redMask = palette.redMask;
      final int greenMask = palette.greenMask;
      final int blueMask = palette.blueMask;
      int newDepth = i.depth;
      int newOrder = ImageData.MSB_FIRST;
      PaletteData newPalette = null;
      switch (i.depth) {
        case 8:
          newDepth = 16;
          newOrder = ImageData.LSB_FIRST;
          newPalette = new PaletteData(0xf800, 0x7e0, 0x1f);
          type = OS.Pg_IMAGE_DIRECT_565;
          break;
        case 16:
          newOrder = ImageData.LSB_FIRST;
          if (((redMask == 0x7c00) && (greenMask == 0x3e0)) && (blueMask == 0x1f)) {
            type = OS.Pg_IMAGE_DIRECT_555;
          } else if (((redMask == 0xf800) && (greenMask == 0x7e0)) && (blueMask == 0x1f)) {
            type = OS.Pg_IMAGE_DIRECT_565;
          } else if (((redMask == 0xf00) && (greenMask == 0xf0)) && (blueMask == 0xf)) {
            type = OS.Pg_IMAGE_DIRECT_444;
          } else {
            type = OS.Pg_IMAGE_DIRECT_565;
            newPalette = new PaletteData(0xf800, 0x7e0, 0x1f);
          }
          break;
        case 24:
          if (((redMask == 0xff) && (greenMask == 0xff00)) && (blueMask == 0xff0000)) {
            type = OS.Pg_IMAGE_DIRECT_888;
          } else {
            type = OS.Pg_IMAGE_DIRECT_888;
            newPalette = new PaletteData(0xff, 0xff00, 0xff0000);
          }
          break;
        case 32:
          if (((redMask == 0xff00) && (greenMask == 0xff0000)) && (blueMask == 0xff000000)) {
            type = OS.Pg_IMAGE_DIRECT_8888;
          } else {
            type = OS.Pg_IMAGE_DIRECT_8888;
            newPalette = new PaletteData(0xff00, 0xff0000, 0xff000000);
          }
          break;
        default:
          SWT.error(ERROR_UNSUPPORTED_DEPTH);
      }
      if (newPalette != null) {
        ImageData img = new ImageData(i.width, i.height, newDepth, newPalette);
        ImageData.blit(
            BLIT_SRC,
            i.data,
            i.depth,
            i.bytesPerLine,
            i.getByteOrder(),
            0,
            0,
            i.width,
            i.height,
            redMask,
            greenMask,
            blueMask,
            ALPHA_OPAQUE,
            null,
            0,
            0,
            0,
            img.data,
            img.depth,
            img.bytesPerLine,
            newOrder,
            0,
            0,
            img.width,
            img.height,
            newPalette.redMask,
            newPalette.greenMask,
            newPalette.blueMask,
            false,
            false);
        if (i.transparentPixel != (-1)) {
          img.transparentPixel = newPalette.getPixel(palette.getRGB(i.transparentPixel));
        }
        img.maskPad = i.maskPad;
        img.maskData = i.maskData;
        img.alpha = i.alpha;
        img.alphaData = i.alphaData;
        i = img;
      }
    }
    int handle = OS.malloc(sizeof);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    PhImage_t phImage = new PhImage_t();
    phImage.type = type;
    phImage.flags = OS.Ph_RELEASE_IMAGE_ALL;
    int size = i.data.length;
    int ptr = OS.malloc(size);
    if (ptr == 0) {
      OS.free(handle);
      SWT.error(ERROR_NO_HANDLES);
    }
    OS.memmove(ptr, i.data, size);
    phImage.image = ptr;
    phImage.size_w = ((short) (i.width));
    phImage.size_h = ((short) (i.height));
    phImage.bpl = i.bytesPerLine;
    if (phPalette != null) {
      size = phPalette.length * 4;
      ptr = OS.malloc(size);
      if (ptr == 0) {
        OS.free(phImage.image);
        OS.free(handle);
        SWT.error(ERROR_NO_HANDLES);
      }
      OS.memmove(ptr, phPalette, size);
      phImage.palette = ptr;
      phImage.colors = phPalette.length;
    }
    if (i.getTransparencyType() == SWT.TRANSPARENCY_MASK) {
      this.type = SWT.ICON;
      int maskBpl = ((i.width * 1) + 7) / 8;
      maskBpl = ((maskBpl + (i.maskPad - 1)) / i.maskPad) * i.maskPad;
      size = maskBpl * i.height;
      ptr = OS.malloc(size);
      if (ptr == 0) {
        if (phImage.palette != 0) {
          OS.free(phImage.palette);
        }
        OS.free(phImage.image);
        OS.free(handle);
        SWT.error(ERROR_NO_HANDLES);
      }
      OS.memmove(ptr, i.maskData, size);
      phImage.mask_bm = ptr;
      phImage.mask_bpl = maskBpl;
    } else {
      this.type = SWT.BITMAP;
      if (i.transparentPixel != (-1)) {
        transparentPixel = i.transparentPixel;
      } else if ((i.alpha != (-1)) || (i.alphaData != null)) {
        PgAlpha_t alpha = new PgAlpha_t();
        alpha.alpha_op = (i.alpha != (-1)) ? OS.Pg_ALPHA_OP_SRC_GLOBAL : OS.Pg_ALPHA_OP_SRC_MAP;
        alpha.alpha_op |= OS.Pg_BLEND_SRC_SRC_ALPHA | OS.Pg_BLEND_DST_ONE_MINUS_SRC_ALPHA;
        alpha.src_global_alpha = ((byte) (i.alpha));
        if ((i.alpha == (-1)) && (i.alphaData != null)) {
          ptr = OS.malloc(i.alphaData.length);
          if (ptr == 0) {
            if (phImage.palette != 0) {
              OS.free(phImage.palette);
            }
            OS.free(phImage.image);
            OS.free(handle);
            SWT.error(ERROR_NO_HANDLES);
          }
          OS.memmove(ptr, i.alphaData, i.alphaData.length);
          alpha.src_alpha_map_bpl = ((short) (i.width));
          alpha.src_alpha_map_dim_w = ((short) (i.width));
          alpha.src_alpha_map_dim_h = ((short) (i.height));
          alpha.src_alpha_map_map = ptr;
        }
        ptr = OS.malloc(sizeof);
        if (ptr == 0) {
          if (alpha.src_alpha_map_map != 0) {
            OS.free(alpha.src_alpha_map_map);
          }
          if (phImage.palette != 0) {
            OS.free(phImage.palette);
          }
          OS.free(phImage.image);
          OS.free(handle);
          SWT.error(ERROR_NO_HANDLES);
        }
        OS.memmove(ptr, alpha, sizeof);
        phImage.alpha = ptr;
      }
    }
    OS.memmove(handle, phImage, sizeof);
    this.handle = handle;
  }
}
