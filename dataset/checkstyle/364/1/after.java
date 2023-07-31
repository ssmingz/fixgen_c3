class PlaceHold {
  protected Checker createChecker(Configuration aCheckConfig) throws Exception {
    final DefaultConfiguration dc = createCheckerConfig(aCheckConfig);
    final Checker c = new Checker();
    final Locale locale = Locale.ENGLISH;
    c.setLocaleCountry(locale.getCountry());
    c.setLocaleLanguage(locale.getLanguage());
    c.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
    c.configure(dc);
    c.addListener(new BriefLogger(mStream));
    return c;
  }
}
