class PlaceHold {
  void setBackground() {
    if (!drawsBackground()) {
      return;
    }
    Control control = findBackgroundControl();
    if (control == null) {
      control = this;
    }
    if (control.backgroundImage != null) {
      setBackgroundImage(control.backgroundImage.handle);
    } else {
      float[] color =
          (control.background != null) ? control.background : control.defaultBackground().handle;
      NSColor nsColor = NSColor.colorWithCalibratedRed(color[0], color[1], color[2], color[3]);
      setBackgroundColor(nsColor);
    }
  }
}
