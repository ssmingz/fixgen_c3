class PlaceHold {
  LRESULT wmNotifyToolTip(NMHDR hdr, int wParam, int lParam) {
    if (OS.IsWinCE) {
      return null;
    }
    switch (hdr.code) {
      case OS.NM_CUSTOMDRAW:
        {
          NMTTCUSTOMDRAW nmcd = new NMTTCUSTOMDRAW();
          OS.MoveMemory(nmcd, lParam, sizeof);
          return wmNotifyToolTip(nmcd, lParam);
        }
      case OS.TTN_SHOW:
        {
          LRESULT result = super.wmNotify(hdr, wParam, lParam);
          if (result != null) {
            return result;
          }
          int pos = OS.GetMessagePos();
          POINT pt = new POINT();
          OS.POINTSTOPOINT(pt, pos);
          OS.ScreenToClient(handle, pt);
          int[] index = new int[1];
          TreeItem[] item = new TreeItem[1];
          RECT[] cellRect = new RECT[1];
          RECT[] itemRect = new RECT[1];
          if (findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
            RECT toolRect = toolTipRect(itemRect[0]);
            OS.MapWindowPoints(handle, 0, toolRect, 2);
            int width = toolRect.right - toolRect.left;
            int height = toolRect.bottom - toolRect.top;
            int flags = (OS.SWP_NOACTIVATE | OS.SWP_NOZORDER) | OS.SWP_NOSIZE;
            if ((hooks(MeasureItem) || hooks(EraseItem)) || hooks(PaintItem)) {
              flags &= ~OS.SWP_NOSIZE;
            }
            SetWindowPos(itemToolTipHandle, 0, toolRect.left, toolRect.top, width, height, flags);
            return LRESULT.ONE;
          }
          return result;
        }
    }
    return null;
  }
}
