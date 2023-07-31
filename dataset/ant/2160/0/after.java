class PlaceHold {
  protected MappedResource(Resource r) {
    wrapped = r;
    isAppendable = wrapped.as(Appendable.class) != null;
  }
}
