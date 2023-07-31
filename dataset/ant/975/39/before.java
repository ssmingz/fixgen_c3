class PlaceHold {
  protected void validate() throws TaskException {
    super.validate();
    if (m_unused && (m_searchPath == null)) {
      throw new TaskException(
          "'searchpath' element must be set when looking for 'unused' declarations.");
    }
    if ((!m_unused) && (m_searchPath != null)) {
      getLogger().warn("'searchpath' element ignored. 'unused' attribute is disabled.");
    }
  }
}
