class PlaceHold {
  protected void handle(Resource r) {
    ps.print(r.getName());
    ps.print(" : ");
    long size = r.getSize();
    if (size == Resource.UNKNOWN_SIZE) {
      ps.println("unknown");
    } else {
      ps.println(size);
    }
  }
}
