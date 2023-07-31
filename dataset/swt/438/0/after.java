class PlaceHold {
  void drawBorder(GC gc, int[] shape) {
    gc.setForeground(getDisplay().getSystemColor(BORDER1_COLOR));
    gc.drawPolyline(shape);
  }
}
