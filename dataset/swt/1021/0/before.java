class PlaceHold {
  public static int carbon_createCIcon(Image image) {
    if (image == null) {
      return 0;
    }
    Rectangle r = image.getBounds();
    short w = ((short) (r.width));
    short h = ((short) (r.height));
    int pm = image.pixmap;
    if ((pm != 0) && (getDepth(pm) > 8)) {
      ImageData id = image.getImageData();
      int[] values = new int[256];
      int fill = 0;
      short depth = 8;
      int bytesPerRow = rowBytes(w, depth);
      byte[] data = new byte[bytesPerRow * h];
      short[] reds = new short[256];
      short[] greens = new short[256];
      short[] blues = new short[256];
      for (int y = 0; y < h; y++) {
        for (int x = 0; x < w; x++) {
          int index = -1;
          int value = id.getPixel(x, y);
          int i;
          for (i = 0; i < fill; i++) {
            if (value == values[i]) {
              index = i;
              break;
            }
          }
          if (i >= fill) {
            index = fill++;
            values[index] = value;
            reds[index] = ((short) ((value >> 16) & 0xff));
            greens[index] = ((short) ((value >> 8) & 0xff));
            blues[index] = ((short) (value & 0xff));
          }
          if (index >= 0) {
            data[(y * bytesPerRow) + x] = ((byte) (index));
          }
        }
      }
      pm = NewPixMap(w, h, depth, bytesPerRow);
      setColorTable(pm, reds, greens, blues);
      OS.setBitMapData(pm, data);
    } else {
      System.out.println("---> CIcon: can use pixmap");
    }
    if (pm != 0) {
      int mask = image.mask;
      if (mask == 0) {
        int rowBytes = rowBytes(w, 1);
        mask = OS.NewBitMap(w, h, ((short) (rowBytes)));
        OS.initBitMapData(mask, rowBytes * h, ((byte) (0xff)));
      }
      int icon = OS.NewCIcon(pm, mask);
      if (mask != image.mask) {
        Dispose(mask);
      }
      return icon;
    }
    return 0;
  }
}
