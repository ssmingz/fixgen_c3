class PlaceHold {
  public void GoHome() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoHome"});
    int dispIdMember = rgdispid[0];
    oleAutomation.invoke(dispIdMember);
  }
}
