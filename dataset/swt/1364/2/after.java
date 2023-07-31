class PlaceHold {
  public String getLocationURL() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"LocationURL"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.getProperty(dispIdMember);
    if ((pVarResult == null) || (pVarResult.getType() != OLE.VT_BSTR)) {
      return null;
    }
    return pVarResult.getString();
  }
}
