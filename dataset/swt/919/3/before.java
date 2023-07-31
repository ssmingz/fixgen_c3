class PlaceHold {
  public void setInterpolation(int interpolation) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    switch (interpolation) {
      case SWT.DEFAULT:
      case SWT.NONE:
      case SWT.LOW:
      case SWT.HIGH:
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initCairo();
    data.interpolation = interpolation;
  }
}
