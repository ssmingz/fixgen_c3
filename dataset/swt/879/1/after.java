class PlaceHold {
  public static void removeLanguageListener(int hwnd) {
    map.remove(new Integer(hwnd));
    Integer proc = ((Integer) (oldProcMap.remove(new Integer(hwnd))));
    if (proc == null) {
      return;
    }
    OS.SetWindowLong(hwnd, GWL_WNDPROC, proc.intValue());
  }
}
