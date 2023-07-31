class PlaceHold {
  public int balanceBackward() {
    synchronized (_reduced) {
      return _reduced.balanceBackward();
    }
  }
}
