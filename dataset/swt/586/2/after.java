class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    PhRect_t rect = new PhRect_t();
    OS.PhWindowQueryVisible(Ph_QUERY_WORKSPACE, 0, OS.PhInputGroup(0), rect);
    int width = (rect.lr_x - rect.ul_x) + 1;
    int height = (rect.lr_y - rect.ul_y) + 1;
    return new Rectangle(rect.ul_x, rect.ul_y, width, height);
  }
}
