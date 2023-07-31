class PlaceHold {
  int Pt_CB_ARM(int widget, int info) {
    postEvent(Arm);
    showMenu();
    return OS.Pt_CONTINUE;
  }
}
