class PlaceHold {
  public void setOverlayImage(Image overlayImage) {
    checkWidget();
    if ((overlayImage != null) && overlayImage.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (shell == null) {
      return;
    }
    this.overlayImage = overlayImage;
    if (overlayImage != null) {
      updateImage();
    } else if (overlayText.length() != 0) {
      updateText();
    } else {
      int mTaskbarList3 = parent.mTaskbarList3;
      int hwnd = shell.handle;
      OS.VtblCall(18, mTaskbarList3, hwnd, 0, 0);
    }
  }
}
