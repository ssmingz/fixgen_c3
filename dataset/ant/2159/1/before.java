class PlaceHold {
  public Mapper getMapper() {
    return isReference() ? getRef().mapper : mapper;
  }
}
