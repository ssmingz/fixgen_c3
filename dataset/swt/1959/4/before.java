class PlaceHold {
  int imageIndex(Image image, int column) {
    if (image == null) {
      return OS.I_IMAGENONE;
    }
    if (column == 0) {
      firstColumnImage = true;
    } else {
      setSubImagesVisible(true);
    }
    if (imageList == null) {
      Rectangle bounds = image.getBounds();
      imageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, bounds.width, bounds.height);
      int index = imageList.indexOf(image);
      if (index == (-1)) {
        index = imageList.add(image);
      }
      int hImageList = imageList.getHandle();
      int topIndex = getTopIndex();
      if (topIndex != 0) {
        setRedraw(false);
        setTopIndex(0);
      }
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, hImageList);
      if (headerImageList != null) {
        int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
        int hHeaderImageList = headerImageList.getHandle();
        OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, hHeaderImageList);
      }
      fixCheckboxImageList(false);
      setItemHeight(false);
      if (topIndex != 0) {
        setTopIndex(topIndex);
        setRedraw(true);
      }
      return index;
    }
    int index = imageList.indexOf(image);
    if (index != (-1)) {
      return index;
    }
    return imageList.add(image);
  }
}
