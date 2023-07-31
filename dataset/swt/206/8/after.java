class PlaceHold {
  public void setFont(Font font) {
    checkWidget();
    Rectangle oldRect = getClientArea();
    super.setFont(font);
    Rectangle newRect = getClientArea();
    if (!oldRect.equals(newRect)) {
      sendResize();
      int index = ((int) (OS.SendMessage(handle, TCM_GETCURSEL, 0, 0)));
      if (index != (-1)) {
        TabItem item = items[index];
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setBounds(getClientArea());
        }
      }
    }
  }
}
