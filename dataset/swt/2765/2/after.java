class PlaceHold {
  public void setOverlayText(String overlayText) {
    checkWidget();
    if (overlayText == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (shell == null) {
      return;
    }
    this.overlayText = overlayText;
    if (overlayText.length() != 0) {
      updateText();
    } else if (overlayImage != null) {
      updateImage();
    } else {
      int mTaskbarList3 = parent.mTaskbarList3;
      int hwnd = shell.handle;
      OS.VtblCall(18, mTaskbarList3, hwnd, 0, 0);
    }
  }
}
