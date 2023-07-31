class PlaceHold {
  void setBackgroundPixel(int pixel) {
    Control control = findImageControl();
    if (control != null) {
      setBackgroundImage(control.backgroundImage);
      return;
    }
    if (OS.IsWindowEnabled(handle)) {
      _setBackgroundPixel(pixel);
    }
    updateFullSelection();
  }
}
