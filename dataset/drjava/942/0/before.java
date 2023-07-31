class PlaceHold {
  public void move(int count) {
    try {
      getRMB().move(count);
      getRMC().move(count);
    } catch (IllegalArgumentException e) {
      resetLocation();
      throw new UnexpectedException(e);
    }
  }
}
