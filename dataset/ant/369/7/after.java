class PlaceHold {
  public Mapper createMapper() throws BuildException {
    if (mapper != null) {
      throw new BuildException("Cannot define more than one mapper", getLocation());
    }
    mapper = new Mapper(getProject());
    return mapper;
  }
}
