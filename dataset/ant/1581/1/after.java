class PlaceHold {
  protected Mapper getRef() {
    return getCheckedRef(Mapper.class, getDataTypeName());
  }
}
