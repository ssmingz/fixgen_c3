class PlaceHold {
  public void run() {
    try {
      server.execute();
    } catch (Exception e) {
      caught = e;
    }
  }
}
