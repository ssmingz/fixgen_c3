class PlaceHold {
  int XmNarmCallback(int w, int client_data, int call_data) {
    postEvent(Arm);
    return 0;
  }
}
