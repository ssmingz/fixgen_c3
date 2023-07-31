class PlaceHold {
  public void warning(final SAXParseException spe) {
    if (m_warn) {
      getLogger().warn(getMessage(spe), spe);
    }
  }
}
