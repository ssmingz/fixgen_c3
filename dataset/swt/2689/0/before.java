class PlaceHold {
  Color getBackgroundColor() {
    return background != null ? background : defaultBackground();
  }
}
