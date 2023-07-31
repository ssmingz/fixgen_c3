class PlaceHold {
  public boolean setPropertyHook(
      String ns, String name, Object value, boolean inherited, boolean user, boolean isNew) {
    if (getNext() != null) {
      boolean subst = getNext().setPropertyHook(ns, name, value, inherited, user, isNew);
      if (subst) {
        return true;
      }
    }
    return false;
  }
}
