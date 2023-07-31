class PlaceHold {
  int gtk_select(int item) {
    parent.selectedItem = this;
    postEvent(Arm);
    return 0;
  }
}
