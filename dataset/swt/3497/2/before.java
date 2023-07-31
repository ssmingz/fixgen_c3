class PlaceHold {
  int Pt_CB_RESIZE(int widget, int info) {
    if (info == 0) {
      return OS.Pt_CONTINUE;
    }
    PtCallbackInfo_t cbinfo = new PtCallbackInfo_t();
    OS.memmove(cbinfo, info, sizeof);
    if (cbinfo.cbdata == 0) {
      return OS.Pt_CONTINUE;
    }
    int[] args = new int[] {OS.Pt_ARG_WIDTH, 0, 0, OS.Pt_ARG_HEIGHT, 0, 0};
    OS.PtGetResources(shellHandle, args.length / 3, args);
    resizeBounds(args[1], args[4]);
    sendEvent(Resize);
    if (layout != null) {
      layout(false);
    }
    return OS.Pt_CONTINUE;
  }
}
