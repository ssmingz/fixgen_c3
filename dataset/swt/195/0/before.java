class PlaceHold {
  int focusHandle() {
    if (entryHandle != 0) {
      return entryHandle;
    }
    return super.focusHandle();
  }
}
