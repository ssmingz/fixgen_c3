class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    int border = getBorder();
    Rect rect = new Rect();
    OS.GetDataBrowserScrollBarInset(handle, rect);
    x -= rect.left + border;
    y -= rect.top + border;
    width += ((rect.left + rect.right) + border) + border;
    height += ((rect.top + rect.bottom) + border) + border;
    return new Rectangle(x, y, width, height);
  }
}
