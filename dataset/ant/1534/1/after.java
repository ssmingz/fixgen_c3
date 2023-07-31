class PlaceHold {
  public Mapper createMapper() throws BuildException {
    if (mapperElement != null) {
      throw new BuildException("Cannot define more than one mapper", getLocation());
    }
    mapperElement = new Mapper(getProject());
    return mapperElement;
  }
}
