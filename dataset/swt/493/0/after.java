class PlaceHold {
  Color getBackgroundColor() {
    return background != null ? Color.cocoa_new(display, background) : defaultBackground();
  }
}
