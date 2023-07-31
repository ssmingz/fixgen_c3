class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    super.setImage(image);
    int hwnd = parent.handle;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = (OS.TVIF_HANDLE | OS.TVIF_IMAGE) | OS.TVIF_SELECTEDIMAGE;
    tvItem.iImage = parent.imageIndex(image);
    tvItem.iSelectedImage = tvItem.iImage;
    tvItem.hItem = handle;
    OS.SendMessage(hwnd, TVM_SETITEM, 0, tvItem);
  }
}
