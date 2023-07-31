class PlaceHold {
  public int add(Image image) {
    int count = OS.ImageList_GetImageCount(handle);
    int index = 0;
    while (index < count) {
      if (images[index] != null) {
        if (images[index].isDisposed()) {
          images[index] = null;
        }
      }
      if (images[index] == null) {
        break;
      }
      index++;
    }
    int hImage = image.handle;
    int[] cx = new int[1];
    int[] cy = new int[1];
    OS.ImageList_GetIconSize(handle, cx, cy);
    switch (image.type) {
      case SWT.BITMAP:
        {
          if (count == 0) {
            BITMAP bm = new BITMAP();
            OS.GetObject(hImage, sizeof, bm);
            cx[0] = bm.bmWidth;
            cy[0] = bm.bmHeight;
            OS.ImageList_SetIconSize(handle, cx[0], cy[0]);
          }
          int hBitmap = copyBitmap(hImage, cx[0], cy[0]);
          int background = -1;
          Color color = image.getBackground();
          if (color != null) {
            background = color.handle;
          }
          if (index == count) {
            OS.ImageList_AddMasked(handle, hBitmap, background);
          } else {
            int hMask = createMask(hBitmap, cx[0], cy[0], background);
            OS.ImageList_Replace(handle, index, hBitmap, hMask);
            OS.DeleteObject(hMask);
          }
          OS.DeleteObject(hBitmap);
          break;
        }
      case SWT.ICON:
        {
          if (count == 0) {
            BITMAP bm = new BITMAP();
            ICONINFO info = new ICONINFO();
            OS.GetIconInfo(hImage, info);
            int hBitmap = info.hbmColor;
            if (hBitmap == 0) {
              hBitmap = info.hbmMask;
            }
            OS.GetObject(hBitmap, sizeof, bm);
            if (hBitmap == info.hbmMask) {
              bm.bmHeight /= 2;
            }
            if (info.hbmColor != 0) {
              OS.DeleteObject(info.hbmColor);
            }
            if (info.hbmMask != 0) {
              OS.DeleteObject(info.hbmMask);
            }
            cx[0] = bm.bmWidth;
            cy[0] = bm.bmHeight;
            OS.ImageList_SetIconSize(handle, cx[0], cy[0]);
          }
          int hIcon = copyIcon(hImage, cx[0], cy[0]);
          OS.ImageList_ReplaceIcon(handle, index == count ? -1 : index, hIcon);
          OS.DestroyIcon(hIcon);
          break;
        }
    }
    if (index == images.length) {
      Image[] newImages = new Image[images.length + 4];
      System.arraycopy(images, 0, newImages, 0, images.length);
      images = newImages;
    }
    images[index] = image;
    return index;
  }
}
