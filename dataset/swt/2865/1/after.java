class PlaceHold {
  int windowProc() {
    if (OS.IsSP) {
      return DialogProc;
    }
    return parent != null ? DialogProc : super.windowProc();
  }
}
