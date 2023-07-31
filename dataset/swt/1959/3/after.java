class PlaceHold {
  int imageIndex(Image image, int index) {
    if (image == null) {
      return OS.I_IMAGENONE;
    }
    if (imageList == null) {
      Rectangle bounds = image.getBounds();
      imageList = display.getImageList(NONE, bounds.width, bounds.height);
    }
    int imageIndex = imageList.indexOf(image);
    if (imageIndex == (-1)) {
      imageIndex = imageList.add(image);
    }
    if ((hwndHeader == 0) || (OS.SendMessage(hwndHeader, HDM_ORDERTOINDEX, 0, 0) == index)) {
      int hImageList = imageList.getHandle();
      int hOldImageList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_NORMAL, 0);
      if (hOldImageList != hImageList) {
        OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_NORMAL, hImageList);
        updateScrollBar();
      }
    }
    return imageIndex;
  }
}
