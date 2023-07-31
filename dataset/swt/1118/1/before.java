class PlaceHold {
  void createHandle() {
    state |= THEME_BACKGROUND;
    NSSlider widget = ((NSSlider) (new SWTSlider().alloc()));
    widget.init();
    widget.setMaxValue(100);
    view = widget;
  }
}
