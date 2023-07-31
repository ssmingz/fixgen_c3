class PlaceHold {
  public int GoSearch() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoSearch"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.invoke(dispIdMember);
    if (pVarResult == null) {
      return 0;
    }
    return pVarResult.getInt();
  }
}
