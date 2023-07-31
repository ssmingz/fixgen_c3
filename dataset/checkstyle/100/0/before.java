class PlaceHold {
  protected void setUp() throws Exception {
    mConfig.setHeaderFile(getPath("java.header"));
    mConfig.setLeftCurlyOptionProperty(LCURLY_METHOD_PROP, NL);
    mConfig.setLeftCurlyOptionProperty(LCURLY_OTHER_PROP, NLOW);
    mConfig.setLeftCurlyOptionProperty(LCURLY_TYPE_PROP, NL);
    mConfig.setRCurly(ALONE);
    mConfig.setStringProperty(LOCALE_COUNTRY_PROP, Locale.ENGLISH.getCountry());
    mConfig.setStringProperty(LOCALE_LANGUAGE_PROP, Locale.ENGLISH.getLanguage());
  }
}
