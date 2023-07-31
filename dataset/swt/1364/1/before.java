class PlaceHold {
  public int getReadyState() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"ReadyState"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.getProperty(dispIdMember);
    if (pVarResult == null) {
      return -1;
    }
    return pVarResult.getInt();
  }
}
