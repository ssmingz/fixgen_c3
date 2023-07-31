class PlaceHold {
  int XmNarmCallback(int w, int client_data, int call_data) {
    sendEvent(Arm);
    return 0;
  }
}
