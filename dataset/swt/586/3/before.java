class PlaceHold {
  public Rectangle getBounds() {
    checkDevice();
    PhRect_t rect = new PhRect_t();
    OS.PhWindowQueryVisible(Ph_QUERY_CONSOLE, 0, 1, rect);
    int width = (rect.lr_x - rect.ul_x) + 1;
    int height = (rect.lr_y - rect.ul_y) + 1;
    return new Rectangle(rect.ul_x, rect.ul_y, width, height);
  }
}
