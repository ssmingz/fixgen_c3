class PlaceHold {
  public Mapper createMapper() throws TaskException {
    if (mapper != null) {
      throw new TaskException("Cannot define more than one mapper");
    }
    mapper = new Mapper(getProject());
    return mapper;
  }
}
