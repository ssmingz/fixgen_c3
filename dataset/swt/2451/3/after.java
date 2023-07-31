class PlaceHold {
  LRESULT wmNotifyChild(NMHDR hdr, int wParam, int lParam) {
    if (OS.COMCTL32_MAJOR >= 6) {
      switch (hdr.code) {
        case OS.NM_RETURN:
        case OS.NM_CLICK:
          NMLINK item = new NMLINK();
          OS.MoveMemory(item, lParam, sizeof);
          Event event = new Event();
          event.text = ids[item.iLink];
          sendSelectionEvent(Selection, event, true);
          break;
      }
    }
    return super.wmNotifyChild(hdr, wParam, lParam);
  }
}
