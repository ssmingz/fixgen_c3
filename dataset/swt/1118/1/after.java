class PlaceHold {
  void createHandle() {
    state |= THEME_BACKGROUND;
    NSSlider widget = ((NSSlider) (new SWTSlider().alloc()));
    widget.init();
    widget.setMaxValue(100);
    widget.setTarget(widget);
    widget.setAction(sel_sendSelection);
    view = widget;
  }
}
