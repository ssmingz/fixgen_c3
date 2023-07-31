class PlaceHold {
  int imageIndexHeader(Image image) {
    if (image == null) {
      return OS.I_IMAGENONE;
    }
    if (headerImageList == null) {
      Rectangle bounds = image.getBounds();
      headerImageList =
          display.getImageList(style & SWT.RIGHT_TO_LEFT, bounds.width, bounds.height);
      int index = headerImageList.indexOf(image);
      if (index == (-1)) {
        index = headerImageList.add(image);
      }
      int hImageList = headerImageList.getHandle();
      int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
      OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hImageList);
      return index;
    }
    int index = headerImageList.indexOf(image);
    if (index != (-1)) {
      return index;
    }
    return headerImageList.add(image);
  }
}
