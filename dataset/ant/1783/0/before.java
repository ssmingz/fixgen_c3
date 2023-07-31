class PlaceHold {
  public void execute() throws TaskException {
    if (m_property == null) {
      throw new TaskException("property attribute is required");
    }
    if (eval()) {
      setProperty(m_property, m_value);
    }
  }
}
