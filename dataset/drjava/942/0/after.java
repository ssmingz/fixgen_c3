class PlaceHold {
  public void move(int count) {
    try {
      _rmb.move(count);
      _rmc.move(count);
    } catch (IllegalArgumentException e) {
      resetLocation();
      throw new UnexpectedException(e);
    }
  }
}
