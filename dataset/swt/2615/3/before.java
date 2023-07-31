class PlaceHold {
  public int GoForward() {
    int[] rgdispid = oleAutomation.getIDsOfNames(new String[] {"GoForward"});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = oleAutomation.invoke(dispIdMember);
    if (pVarResult == null) {
      return 0;
    }
    return pVarResult.getInt();
  }
}
