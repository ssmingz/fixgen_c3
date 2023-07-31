class PlaceHold {
  public Mapper createMapper() throws TaskException {
    if (mapperElement != null) {
      throw new TaskException("Cannot define more than one mapper");
    }
    mapperElement = new Mapper(getProject());
    return mapperElement;
  }
}
