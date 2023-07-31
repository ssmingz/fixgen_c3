class PlaceHold {
  public void Navigate(String url) {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"Navigate", "URL"});
    int dispIdMember = rgdispid[0];
    Variant[] rgvarg = new Variant[1];
    rgvarg[0] = new Variant(url);
    int[] rgdispidNamedArgs = new int[1];
    rgdispidNamedArgs[0] = rgdispid[1];
    oleAutomation.invoke(dispIdMember, rgvarg, rgdispidNamedArgs);
  }
}
