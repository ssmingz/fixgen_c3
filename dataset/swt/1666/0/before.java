class PlaceHold {
  Control findBackgroundControl() {
    if ((backgroundImage != null) || (background != null)) {
      return this;
    }
    return (!isTransparent()) && ((state & PARENT_BACKGROUND) != 0)
        ? parent.findBackgroundControl()
        : null;
  }
}
