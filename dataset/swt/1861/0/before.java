class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    int[] args =
        new int[] {
          OS.Pt_ARG_LIST_TOTAL_HEIGHT,
          0,
          0,
          OS.Pt_ARG_LIST_ITEM_COUNT,
          0,
          0,
          OS.Pt_ARG_LIST_FONT,
          0,
          0
        };
    OS.PtGetResources(handle, args.length / 3, args);
    if (args[4] == 0) {
      int ptr = OS.malloc(1);
      PhRect_t rect = new PhRect_t();
      OS.PfExtentText(rect, null, args[7], ptr, 1);
      OS.free(ptr);
      int inset = 4;
      return inset + ((rect.lr_y - rect.ul_y) + 1);
    }
    return args[1] / args[4];
  }
}
