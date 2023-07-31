class PlaceHold {
  public Mapper createMapper() throws BuildException {
    if (mapperElement != null) {
      throw new BuildException("Cannot define more than one mapper");
    }
    mapperElement = new Mapper(project);
    return mapperElement;
  }
}
