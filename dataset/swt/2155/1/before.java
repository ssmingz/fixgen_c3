class PlaceHold {
  public ImageData getImageData() {
    String iconPath = null;
    switch (getDesktop(display)) {
      case DESKTOP_KDE:
        {
          byte[] buffer = Converter.wcsToMbcs(null, name, true);
          int mimeTypeName = KDE.QString_new(buffer);
          int mimeType = KDE.KMimeType_mimeType(mimeTypeName);
          KDE.QString_delete(mimeTypeName);
          if (mimeType == 0) {
            return null;
          }
          int mimeIcon = KDE.KMimeType_icon(mimeType, 0, 0);
          int loader = KDE.KGlobal_iconLoader();
          int path = KDE.KIconLoader_iconPath(loader, mimeIcon, KICON_SMALL, 1);
          if (path == 0) {
            return null;
          }
          iconPath = kde_convertQStringAndFree(path);
          break;
        }
      case DESKTOP_GNOME:
        {
          return null;
        }
      case DESKTOP_CDE:
        {
          return cde_getImageData();
        }
      case DESKTOP_UNKNOWN:
        {
          return null;
        }
    }
    if (iconPath.endsWith("xpm")) {
      int xDisplay = display.xDisplay;
      int screen = OS.XDefaultScreenOfDisplay(xDisplay);
      int fgPixel = OS.XWhitePixel(display.xDisplay, OS.XDefaultScreen(xDisplay));
      int bgPixel = OS.XBlackPixel(display.xDisplay, OS.XDefaultScreen(xDisplay));
      byte[] iconName = Converter.wcsToMbcs(null, iconPath, true);
      int pixmap = OS.XmGetPixmap(screen, iconName, fgPixel, bgPixel);
      if (pixmap == OS.XmUNSPECIFIED_PIXMAP) {
        return null;
      }
      Image image = Image.motif_new(display, BITMAP, pixmap, 0);
      ImageData imageData = image.getImageData();
      OS.XmDestroyPixmap(screen, pixmap);
      return imageData;
    }
    try {
      return new ImageData(iconPath);
    } catch (Exception e) {
      return null;
    }
  }
}
