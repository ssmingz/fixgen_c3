class PlaceHold {
  public void GoBack() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoBack"});
    int dispIdMember = rgdispid[0];
    oleAutomation.invoke(dispIdMember);
  }
}
