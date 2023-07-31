class PlaceHold {
  public String getLocationName() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"LocationName"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.getProperty(dispIdMember);
    if (pVarResult == null) {
      return null;
    }
    return pVarResult.getString();
  }
}
