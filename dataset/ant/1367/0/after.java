class PlaceHold {
  public static MappedResource map(Resource r) {
    return r.as(FileProvider.class) != null ? new FileProviderMR(r) : new MappedResource(r);
  }
}
