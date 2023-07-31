class PlaceHold {
  public void select(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if ((style & SWT.SINGLE) != 0) {
      int hItem = item.handle;
      int state = 0;
      if (OS.IsWinCE) {
        TVITEM tvItem = new TVITEM();
        tvItem.hItem = hItem;
        tvItem.mask = OS.TVIF_STATE;
        OS.SendMessage(handle, TVM_GETITEM, 0, tvItem);
        state = tvItem.state;
      } else {
        state = ((int) (OS.SendMessage(handle, TVM_GETITEMSTATE, hItem, TVIS_SELECTED)));
      }
      if ((state & OS.TVIS_SELECTED) != 0) {
        return;
      }
      SCROLLINFO hInfo = null;
      int bits = OS.GetWindowLong(handle, GWL_STYLE);
      if ((bits & (OS.TVS_NOHSCROLL | OS.TVS_NOSCROLL)) == 0) {
        hInfo = new SCROLLINFO();
        hInfo.cbSize = SCROLLINFO.sizeof;
        hInfo.fMask = OS.SIF_ALL;
        OS.GetScrollInfo(handle, SB_HORZ, hInfo);
      }
      SCROLLINFO vInfo = new SCROLLINFO();
      vInfo.cbSize = SCROLLINFO.sizeof;
      vInfo.fMask = OS.SIF_ALL;
      OS.GetScrollInfo(handle, SB_VERT, vInfo);
      boolean redraw = (drawCount == 0) && OS.IsWindowVisible(handle);
      if (redraw) {
        OS.UpdateWindow(handle);
        OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
      }
      setSelection(item);
      if (hInfo != null) {
        int hThumb = OS.MAKELPARAM(SB_THUMBPOSITION, hInfo.nPos);
        OS.SendMessage(handle, WM_HSCROLL, hThumb, 0);
      }
      if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
        OS.SetScrollInfo(handle, SB_VERT, vInfo, true);
      }
      int vThumb = OS.MAKELPARAM(SB_THUMBPOSITION, vInfo.nPos);
      OS.SendMessage(handle, WM_VSCROLL, vThumb, 0);
      if (redraw) {
        OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
        OS.InvalidateRect(handle, null, true);
        if ((style & SWT.DOUBLE_BUFFERED) == 0) {
          int oldStyle = style;
          style |= SWT.DOUBLE_BUFFERED;
          OS.UpdateWindow(handle);
          style = oldStyle;
        }
      }
      return;
    }
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_STATE;
    tvItem.stateMask = OS.TVIS_SELECTED;
    tvItem.state = OS.TVIS_SELECTED;
    tvItem.hItem = item.handle;
    OS.SendMessage(handle, TVM_SETITEM, 0, tvItem);
  }
}
