class PlaceHold {
  int imageIndex(Image image) {
    if ((hwndCB == 0) || (image == null)) {
      return OS.I_IMAGENONE;
    }
    if (imageList == null) {
      Rectangle bounds = image.getBounds();
      imageList = display.getImageList(style & SWT.RIGHT_TO_LEFT, bounds.width, bounds.height);
      int index = imageList.add(image);
      int hImageList = imageList.getHandle();
      OS.SendMessage(hwndCB, TB_SETIMAGELIST, 0, hImageList);
      return index;
    }
    int index = imageList.indexOf(image);
    if (index == (-1)) {
      index = imageList.add(image);
    } else {
      imageList.put(index, image);
    }
    return index;
  }
}
