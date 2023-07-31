class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    this.image = image;
    if (parent != null) {
      return;
    }
    if (display.dockImage == 0) {
      if (image != null) {
        OS.SetApplicationDockTileImage(image.handle);
      } else {
        OS.RestoreApplicationDockTileImage();
      }
    }
  }
}
