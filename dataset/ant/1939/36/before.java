class PlaceHold {
  public Marker createSrcfile() throws TaskException {
    if (m_srcFilePos != null) {
      throw new TaskException(getName() + " doesn\'t support multiple srcfile elements.");
    }
    m_srcFilePos = getCommand().createMarker();
    return m_srcFilePos;
  }
}
