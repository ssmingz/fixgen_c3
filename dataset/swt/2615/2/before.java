class PlaceHold {
  public int GoBack() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoBack"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.invoke(dispIdMember);
    if (pVarResult == null) {
      return 0;
    }
    return pVarResult.getInt();
  }
}
