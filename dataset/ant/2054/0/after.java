class PlaceHold {
  public Mapper getMapper() {
    if (isReference()) {
      return getRef().mapper;
    }
    dieOnCircularReference();
    return mapper;
  }
}
