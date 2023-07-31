class PlaceHold {
  public boolean close() {
    boolean result = true;
    int[] rgdispid = auto.getIDsOfNames(new String[] {PROPERTY_DOCUMENT});
    int dispIdMember = rgdispid[0];
    Variant pVarResult = auto.getProperty(dispIdMember);
    if ((pVarResult == null) || (pVarResult.getType() == COM.VT_EMPTY)) {
      if (pVarResult != null) {
        pVarResult.dispose();
      }
    } else {
      OleAutomation document = pVarResult.getAutomation();
      pVarResult.dispose();
      rgdispid = document.getIDsOfNames(new String[] {"parentWindow"});
      if (rgdispid != null) {
        dispIdMember = rgdispid[0];
        pVarResult = document.getProperty(dispIdMember);
        if ((pVarResult == null) || (pVarResult.getType() == COM.VT_EMPTY)) {
          if (pVarResult != null) {
            pVarResult.dispose();
          }
        } else {
          OleAutomation window = pVarResult.getAutomation();
          pVarResult.dispose();
          rgdispid = window.getIDsOfNames(new String[] {"location"});
          dispIdMember = rgdispid[0];
          pVarResult = window.getProperty(dispIdMember);
          if ((pVarResult == null) || (pVarResult.getType() == COM.VT_EMPTY)) {
            if (pVarResult != null) {
              pVarResult.dispose();
            }
          } else {
            OleAutomation location = pVarResult.getAutomation();
            pVarResult.dispose();
            LocationListener[] oldListeners = locationListeners;
            locationListeners = new LocationListener[0];
            rgdispid = location.getIDsOfNames(new String[] {"replace"});
            dispIdMember = rgdispid[0];
            Variant[] args = new Variant[] {new Variant("about:blank")};
            pVarResult = location.invoke(dispIdMember, args);
            if (pVarResult == null) {
              result = false;
            } else {
              pVarResult.dispose();
            }
            args[0].dispose();
            locationListeners = oldListeners;
            location.dispose();
          }
          window.dispose();
        }
      }
      document.dispose();
    }
    return result;
  }
}
