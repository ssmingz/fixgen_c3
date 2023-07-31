class PlaceHold {
  boolean setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    boolean changed = super.setBounds(x, y, width, height, move, resize);
    if (changed && resize) {
      resizeClientArea(width, height);
    }
    return changed;
  }
}
