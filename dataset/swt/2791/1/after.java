class PlaceHold {
  public void GoForward() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoForward"});
    int dispIdMember = rgdispid[0];
    oleAutomation.invoke(dispIdMember);
  }
}
