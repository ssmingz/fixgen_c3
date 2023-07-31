class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    PMRect pageRect = new PMRect();
    OS.PMGetAdjustedPageRect(pageFormat, pageRect);
    return new Rectangle(
        0, 0, ((int) (pageRect.right - pageRect.left)), ((int) (pageRect.bottom - pageRect.top)));
  }
}
