class PlaceHold {
  int imageIndex(Image image) {
    if (image == null) {
      return OS.I_IMAGENONE;
    }
    if (imageList == null) {
      Rectangle bounds = image.getBounds();
      imageList = display.getImageList(new Point(bounds.width, bounds.height));
      int index = imageList.indexOf(image);
      if (index == (-1)) {
        index = imageList.add(image);
      }
      int hImageList = imageList.getHandle();
      int topIndex = getTopIndex();
      setRedraw(false);
      setTopIndex(0);
      OS.SendMessage(handle, LVM_SETIMAGELIST, LVSIL_SMALL, hImageList);
      setTopIndex(topIndex);
      fixCheckboxImageList();
      setRedraw(true);
      return index;
    }
    int index = imageList.indexOf(image);
    if (index != (-1)) {
      return index;
    }
    return imageList.add(image);
  }
}
