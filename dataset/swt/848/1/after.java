class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (!OS.IsWinCE) {
      if (OS.IsIconic(handle)) {
        return super.getBounds();
      }
    }
    RECT rect = new RECT();
    OS.GetWindowRect(handle, rect);
    int width = rect.right - rect.left;
    int height = rect.bottom - rect.top;
    return new Rectangle(rect.left, rect.top, width, height);
  }
}
