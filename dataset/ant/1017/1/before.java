class PlaceHold {
  public String getName() throws TaskException {
    if (m_name == null) {
      throw new TaskException("Name attribute is missing.");
    }
    return m_name;
  }
}
