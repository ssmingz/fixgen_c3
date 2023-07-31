class PlaceHold {
  Control findBackgroundControl() {
    if (((background != (-1)) || (backgroundImage != null)) && (backgroundAlpha > 0)) {
      return this;
    }
    return (state & PARENT_BACKGROUND) != 0 ? parent.findBackgroundControl() : null;
  }
}
