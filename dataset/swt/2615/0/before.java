class PlaceHold {
  public int GoHome() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoHome"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.invoke(dispIdMember);
    if (pVarResult == null) {
      return 0;
    }
    return pVarResult.getInt();
  }
}
