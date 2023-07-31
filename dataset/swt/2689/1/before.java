class PlaceHold {
  Color getForegroundColor() {
    return foreground != null ? foreground : defaultForeground();
  }
}
