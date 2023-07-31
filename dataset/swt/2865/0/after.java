class PlaceHold {
  TCHAR windowClass() {
    if (OS.IsSP) {
      return DialogClass;
    }
    return parent != null ? DialogClass : super.windowClass();
  }
}
