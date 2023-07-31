class PlaceHold {
  LRESULT WM_MOUSEMOVE(int wParam, int lParam) {
    int pos = OS.GetMessagePos();
    if (pos != display.lastMouse) {
      if (!OS.IsWinCE) {
        boolean mouseEnter = hooks(MouseEnter) || display.filters(MouseEnter);
        boolean mouseExit = hooks(MouseExit) || display.filters(MouseExit);
        boolean mouseHover = hooks(MouseHover) || display.filters(MouseHover);
        if ((mouseEnter || mouseExit) || mouseHover) {
          TRACKMOUSEEVENT lpEventTrack = new TRACKMOUSEEVENT();
          lpEventTrack.cbSize = TRACKMOUSEEVENT.sizeof;
          lpEventTrack.dwFlags = OS.TME_QUERY;
          lpEventTrack.hwndTrack = handle;
          OS.TrackMouseEvent(lpEventTrack);
          if (lpEventTrack.dwFlags == 0) {
            lpEventTrack.dwFlags = OS.TME_LEAVE | OS.TME_HOVER;
            lpEventTrack.hwndTrack = handle;
            OS.TrackMouseEvent(lpEventTrack);
            if (mouseEnter) {
              MSG msg = new MSG();
              while (OS.PeekMessage(msg, 0, WM_MOUSELEAVE, WM_MOUSELEAVE, PM_REMOVE)) {
                OS.TranslateMessage(msg);
                OS.DispatchMessage(msg);
              }
              sendMouseEvent(MouseEnter, 0, WM_MOUSEMOVE, wParam, lParam);
            }
          } else {
            lpEventTrack.dwFlags = OS.TME_HOVER;
            OS.TrackMouseEvent(lpEventTrack);
          }
        }
      }
      display.lastMouse = pos;
      sendMouseEvent(MouseMove, 0, WM_MOUSEMOVE, wParam, lParam);
    }
    return null;
  }
}
