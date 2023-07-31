class PlaceHold {
  public void setMemberPat(String aMemberPat) throws RESyntaxException {
    mMemberRegexp = new RE(aMemberPat);
    mMemberPat = aMemberPat;
  }
}
