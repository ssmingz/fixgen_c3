class PlaceHold {
  public void stdout(String line) {
    getContext().debug(line);
    if (null != m_labelSpec) {
      if (util.match("/^Options:/", line)) {
        line = "Options: " + m_lock;
      }
      m_labelSpec.append(line + "\n");
    }
  }
}
