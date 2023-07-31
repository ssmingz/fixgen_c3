class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (index == 0) {
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(this.image)) {
          return;
        }
      }
      super.setImage(image);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if ((images == null) && (index != 0)) {
      images = new Image[count];
    }
    if (images != null) {
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(images[index])) {
          return;
        }
      }
      images[index] = image;
    }
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    parent.imageIndex(image, index);
    if (index == 0) {
      if (this != parent.currentItem) {
        int hwnd = parent.handle;
        TVITEM tvItem = new TVITEM();
        tvItem.mask = (OS.TVIF_HANDLE | OS.TVIF_IMAGE) | OS.TVIF_SELECTEDIMAGE;
        tvItem.hItem = handle;
        tvItem.iImage = tvItem.iSelectedImage = OS.I_IMAGECALLBACK;
        tvItem.mask |= OS.TVIF_TEXT;
        tvItem.pszText = OS.LPSTR_TEXTCALLBACK;
        OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
      }
    } else {
      redraw(index, false, true);
    }
  }
}
