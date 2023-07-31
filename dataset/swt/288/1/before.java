class PlaceHold {
  public void quadTo(float cx, float cy, float x, float y) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    double[] currentX = new double[1];
    double[] currentY = new double[1];
    Cairo.cairo_current_point(handle, currentX, currentY);
    if (!move) {
      Cairo.cairo_move_to(handle, currentX[0], currentY[0]);
    }
    move = true;
    float x0 = ((float) (currentX[0]));
    float y0 = ((float) (currentY[0]));
    float cx1 = x0 + ((2 * (cx - x0)) / 3);
    float cy1 = y0 + ((2 * (cy - y0)) / 3);
    float cx2 = cx1 + ((x - x0) / 3);
    float cy2 = cy1 + ((y - y0) / 3);
    Cairo.cairo_curve_to(handle, cx1, cy1, cx2, cy2, x, y);
  }
}
