class PlaceHold {
  protected final void log(int aLine, String aKey, Object[] aArgs) {
    mMessages.add(new LocalizedMessage(aLine, getResourceBundle(), aKey, aArgs, mSeverityLevel));
  }
}
