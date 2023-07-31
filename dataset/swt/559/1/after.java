class PlaceHold {
  public void setBackgroundImage(Image image) {
    checkWidget();
    if (image != null) {
      if (image.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
      if (image.type != SWT.BITMAP) {
        error(ERROR_INVALID_ARGUMENT);
      }
    }
    if ((backgroundImage == image) && (backgroundAlpha > 0)) {
      return;
    }
    backgroundAlpha = 255;
    backgroundImage = image;
    Shell shell = getShell();
    shell.releaseBrushes();
    updateBackgroundImage();
  }
}
