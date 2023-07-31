class PlaceHold {
  void updateBackgroundColor() {
    Control control = findBackgroundControl();
    if (control == null) {
      control = this;
    }
    float[] color =
        (control.background != null) ? control.background : control.defaultBackground().handle;
    NSColor nsColor = NSColor.colorWithDeviceRed(color[0], color[1], color[2], color[3]);
    setBackgroundColor(nsColor);
  }
}
