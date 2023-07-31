class PlaceHold {
  public static MappedResource map(Resource r) {
    return r instanceof FileProvider ? new FileProviderMR(r) : new MappedResource(r);
  }
}
