class PlaceHold {
  void updateBar(int selection, int minimum, int maximum, int thumb) {
    NSScroller widget = ((NSScroller) (view));
    selection = Math.max(minimum, Math.min(maximum - thumb, selection));
    int range = (maximum - thumb) - minimum;
    float fraction = (range <= 0) ? 1 : ((float) (selection - minimum)) / range;
    float knob = (range <= 0) ? 1 : ((float) (thumb)) / (maximum - minimum);
    double oldFraction = widget.doubleValue();
    double oldKnob = widget.knobProportion();
    widget.setDoubleValue(fraction);
    widget.setKnobProportion(knob);
    widget.setEnabled(range > 0);
    if (((OS.VERSION >= 0x1070) && (target == null))
        && ((knob != oldKnob) || (fraction != oldFraction))) {
      OS.objc_msgSend(parent.scrollView.id, sel_flashScrollers);
    }
  }
}
