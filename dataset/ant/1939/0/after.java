class PlaceHold {
  public Marker createTargetfile() throws TaskException {
    if (m_targetFilePos != null) {
      throw new TaskException(
          getContext().getName() + " doesn\'t support multiple targetfile elements.");
    }
    m_targetFilePos = getCommand().createMarker();
    m_srcIsFirst = m_srcFilePos != null;
    return m_targetFilePos;
  }
}
