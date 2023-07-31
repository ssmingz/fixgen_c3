class PlaceHold {
  public void GoSearch() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoSearch"});
    int dispIdMember = rgdispid[0];
    oleAutomation.invoke(dispIdMember);
  }
}
