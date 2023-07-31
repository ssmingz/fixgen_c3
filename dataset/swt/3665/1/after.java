class PlaceHold {
  public int internal_WM_GETOBJECT(int wParam, int lParam) {
    if (objIAccessible == null) {
      return 0;
    }
    if (((int) (lParam)) == COM.OBJID_CLIENT) {
      return COM.LresultFromObject(IIDIAccessible, wParam, objIAccessible.getAddress());
    }
    return 0;
  }
}
