class PlaceHold {
  public void setInterpolation(int interpolation) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((data.gdipGraphics == 0) && (interpolation == SWT.DEFAULT)) {
      return;
    }
    int mode = 0;
    switch (interpolation) {
      case SWT.DEFAULT:
        mode = Gdip.InterpolationModeDefault;
        break;
      case SWT.NONE:
        mode = Gdip.InterpolationModeNearestNeighbor;
        break;
      case SWT.LOW:
        mode = Gdip.InterpolationModeLowQuality;
        break;
      case SWT.HIGH:
        mode = Gdip.InterpolationModeHighQuality;
        break;
      default:
        SWT.error(ERROR_INVALID_ARGUMENT);
    }
    initGdip(false, false);
    Gdip.Graphics_SetInterpolationMode(data.gdipGraphics, mode);
  }
}
