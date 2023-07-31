class PlaceHold {
  int get_accParent(int ppdispParent) {
    if (iaccessible == null) {
      return COM.CO_E_OBJNOTCONNECTED;
    }
    return iaccessible.get_accParent(ppdispParent);
  }
}
