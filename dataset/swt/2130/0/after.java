class PlaceHold {
  public void getPixels(int x, int y, int getWidth, byte[] pixels, int startIndex) {
    if (pixels == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (((((getWidth < 0) || (x >= width)) || (y >= height)) || (x < 0)) || (y < 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (getWidth == 0) {
      return;
    }
    int index;
    int theByte;
    int mask = 0;
    int n = getWidth;
    int i = startIndex;
    int srcX = x;
    int srcY = y;
    if (depth == 1) {
      index = (y * bytesPerLine) + (x >> 3);
      theByte = data[index] & 0xff;
      while (n > 0) {
        mask = 1 << (7 - (srcX & 0x7));
        if ((theByte & mask) == 0) {
          pixels[i] = 0;
        } else {
          pixels[i] = 1;
        }
        i++;
        n--;
        srcX++;
        if (srcX >= width) {
          srcY++;
          index = srcY * bytesPerLine;
          if (n > 0) {
            theByte = data[index] & 0xff;
          }
          srcX = 0;
        } else if (mask == 1) {
          index++;
          if (n > 0) {
            theByte = data[index] & 0xff;
          }
        }
      }
      return;
    }
    if (depth == 2) {
      index = (y * bytesPerLine) + (x >> 2);
      theByte = data[index] & 0xff;
      int offset;
      while (n > 0) {
        offset = 3 - (srcX % 4);
        mask = 3 << (offset * 2);
        pixels[i] = ((byte) ((theByte & mask) >> (offset * 2)));
        i++;
        n--;
        srcX++;
        if (srcX >= width) {
          srcY++;
          index = srcY * bytesPerLine;
          if (n > 0) {
            theByte = data[index] & 0xff;
          }
          srcX = 0;
        } else if (offset == 0) {
          index++;
          theByte = data[index] & 0xff;
        }
      }
      return;
    }
    if (depth == 4) {
      index = (y * bytesPerLine) + (x >> 1);
      if ((x & 0x1) == 1) {
        theByte = data[index] & 0xff;
        pixels[i] = ((byte) (theByte & 0xf));
        i++;
        n--;
        srcX++;
        if (srcX >= width) {
          srcY++;
          index = srcY * bytesPerLine;
          srcX = 0;
        } else {
          index++;
        }
      }
      while (n > 1) {
        theByte = data[index] & 0xff;
        pixels[i] = ((byte) (theByte >> 4));
        i++;
        n--;
        srcX++;
        if (srcX >= width) {
          srcY++;
          index = srcY * bytesPerLine;
          srcX = 0;
        } else {
          pixels[i] = ((byte) (theByte & 0xf));
          i++;
          n--;
          srcX++;
          if (srcX >= width) {
            srcY++;
            index = srcY * bytesPerLine;
            srcX = 0;
          } else {
            index++;
          }
        }
      }
      if (n > 0) {
        theByte = data[index] & 0xff;
        pixels[i] = ((byte) (theByte >> 4));
      }
      return;
    }
    if (depth == 8) {
      index = (y * bytesPerLine) + x;
      for (int j = 0; j < getWidth; j++) {
        pixels[i] = data[index];
        i++;
        srcX++;
        if (srcX >= width) {
          srcY++;
          index = srcY * bytesPerLine;
          srcX = 0;
        } else {
          index++;
        }
      }
      return;
    }
    SWT.error(ERROR_UNSUPPORTED_DEPTH);
  }
}
