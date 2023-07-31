class PlaceHold {
  public void warning(final SAXParseException spe) {
    if (m_warn) {
      m_context.warn(getMessage(spe), spe);
    }
  }
}
