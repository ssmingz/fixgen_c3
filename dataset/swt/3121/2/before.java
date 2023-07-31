class PlaceHold {
  public Rectangle getClientArea() {
    checkWidget();
    int border = getBorder();
    Rect rect = new Rect();
    Rect inset = new Rect();
    OS.GetControlBounds(handle, rect);
    OS.GetDataBrowserScrollBarInset(handle, inset);
    int width = Math.max(0, (((rect.right - rect.left) - inset.right) - border) - border);
    int height = Math.max(0, (((rect.bottom - rect.top) - inset.bottom) - border) - border);
    return new Rectangle(inset.left + border, inset.top + border, width, height);
  }
}
