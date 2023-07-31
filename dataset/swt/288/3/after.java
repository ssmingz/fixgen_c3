class PlaceHold {
  public void cubicTo(float cx1, float cy1, float cx2, float cy2, float x, float y) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (!move) {
      double[] currentX = new double[1];
      double[] currentY = new double[1];
      Cairo.cairo_get_current_point(handle, currentX, currentY);
      Cairo.cairo_move_to(handle, currentX[0], currentY[0]);
    }
    move = true;
    Cairo.cairo_curve_to(handle, cx1, cy1, cx2, cy2, x, y);
  }
}
