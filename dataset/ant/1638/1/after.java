class PlaceHold {
  public void add(ResourceCollection rc) {
    if (resources == null) {
      resources = new Union();
    }
    resources.add(rc);
  }
}
