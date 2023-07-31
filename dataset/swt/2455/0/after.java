class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setImage(image);
    if (OS.COMCTL32_MAJOR >= 6) {
      if (text.indexOf('&') != (-1)) {
        setText(text);
      }
    }
    int hwnd = parent.handle;
    TCITEM tcItem = new TCITEM();
    tcItem.mask = OS.TCIF_IMAGE;
    tcItem.iImage = parent.imageIndex(image);
    OS.SendMessage(hwnd, TCM_SETITEM, index, tcItem);
  }
}
