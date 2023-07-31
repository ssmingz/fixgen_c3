class PlaceHold {
  void update(boolean all) {
    int oldHeaderProc = 0;
    int oldTableProc = 0;
    int hwndHeader = OS.SendMessage(handle, LVM_GETHEADER, 0, 0);
    boolean fixSubclass = ((!hasChildren()) && (!hooks(Paint))) && (!filters(Paint));
    if (fixSubclass) {
      oldTableProc = OS.SetWindowLongPtr(handle, GWLP_WNDPROC, TableProc);
      oldHeaderProc = OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, HeaderProc);
    }
    super.update(all);
    if (fixSubclass) {
      OS.SetWindowLongPtr(handle, GWLP_WNDPROC, oldTableProc);
      OS.SetWindowLongPtr(hwndHeader, GWLP_WNDPROC, oldHeaderProc);
    }
  }
}
