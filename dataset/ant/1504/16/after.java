class PlaceHold {
  public Mapper createMapper() throws TaskException {
    if (m_mapperElement != null) {
      throw new TaskException("Cannot define more than one mapper");
    }
    m_mapperElement = new Mapper(getProject());
    return m_mapperElement;
  }
}
