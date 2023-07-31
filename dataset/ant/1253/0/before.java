class PlaceHold {
  public void error(final SAXParseException spe) {
    m_failed = true;
    getLogger().error(getMessage(spe), spe);
  }
}
