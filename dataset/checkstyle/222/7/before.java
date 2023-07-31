class PlaceHold {
  public void setPublicMemberPat(String aPublicMemberPat) throws RESyntaxException {
    mPublicMemberRegexp = new RE(aPublicMemberPat);
    mPublicMemberPat = aPublicMemberPat;
  }
}
