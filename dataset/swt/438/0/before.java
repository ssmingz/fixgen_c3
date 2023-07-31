class PlaceHold {
  void drawBorder(GC gc, int[] shape) {
    gc.setForeground(borderColor);
    gc.drawPolyline(shape);
  }
}
