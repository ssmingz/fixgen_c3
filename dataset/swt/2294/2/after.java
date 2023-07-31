class PlaceHold {
  public int internal_new_GC(GCData data) {
    checkWidget();
    int phGC = OS.PgCreateGC(0);
    if (phGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int[] args = new int[] {OS.Pt_ARG_COLOR, 0, 0, OS.Pt_ARG_FILL_COLOR, 0, 0};
    OS.PtGetResources(handle, args.length / 3, args);
    data.device = display;
    data.widget = handle;
    data.topWidget = topHandle();
    data.foreground = args[1];
    data.background = args[4];
    data.font = getFont().handle;
    return phGC;
  }
}
