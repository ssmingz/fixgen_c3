class PlaceHold {
  public Rectangle getBounds() {
    if (handle == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    PhImage_t image = new PhImage_t();
    OS.memmove(image, handle, sizeof);
    return new Rectangle(0, 0, image.size_w, image.size_h);
  }
}
