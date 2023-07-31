class PlaceHold {
  Control findBackgroundControl() {
    Control control = super.findBackgroundControl();
    if ((OS.COMCTL32_MAJOR < 6) || (!OS.IsAppThemed())) {
      if (control == null) {
        control = this;
      }
    }
    return control;
  }
}
