class PlaceHold {
  Control findBackgroundControl() {
    Control control = super.findBackgroundControl();
    if (!isAppThemed()) {
      if (control == null) {
        control = this;
      }
    }
    return control;
  }
}
