class PlaceHold {
  public void dispose() {
    if (display == null) {
      return;
    }
    display = null;
  }
}
