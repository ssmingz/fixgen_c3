class PlaceHold {
  void setCheckboxImageList() {
    if ((style & SWT.CHECK) == 0) {
      return;
    }
    int count = 5;
    int height = OS.SendMessage(handle, TVM_GETITEMHEIGHT, 0, 0);
    int width = height;
    int flags = ImageList.COLOR_FLAGS;
    if ((style & SWT.RIGHT_TO_LEFT) != 0) {
      flags |= OS.ILC_MIRROR;
    }
    if ((OS.COMCTL32_MAJOR < 6) || (!OS.IsAppThemed())) {
      flags |= OS.ILC_MASK;
    }
    int hNewStateList = OS.ImageList_Create(width, height, flags, count, count);
    int hDC = OS.GetDC(handle);
    int memDC = OS.CreateCompatibleDC(hDC);
    int hBitmap = OS.CreateCompatibleBitmap(hDC, width * count, height);
    int hOldBitmap = OS.SelectObject(memDC, hBitmap);
    RECT rect = new RECT();
    OS.SetRect(rect, 0, 0, width * count, height);
    int clrBackground = _getBackgroundPixel();
    int hBrush = OS.CreateSolidBrush(clrBackground);
    OS.FillRect(memDC, rect, hBrush);
    OS.DeleteObject(hBrush);
    int oldFont = OS.SelectObject(hDC, defaultFont());
    TEXTMETRIC tm = (OS.IsUnicode) ? ((TEXTMETRIC) (new TEXTMETRICW())) : new TEXTMETRICA();
    OS.GetTextMetrics(hDC, tm);
    OS.SelectObject(hDC, oldFont);
    int itemWidth = Math.min(tm.tmHeight, width);
    int itemHeight = Math.min(tm.tmHeight, height);
    int left = (width - itemWidth) / 2;
    int top = ((height - itemHeight) / 2) + 1;
    OS.SetRect(rect, left + width, top, (left + width) + itemWidth, top + itemHeight);
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      int hTheme = OS.OpenThemeData(handle, BUTTON);
      OS.DrawThemeBackground(hTheme, memDC, BP_CHECKBOX, CBS_UNCHECKEDNORMAL, rect, null);
      rect.left += width;
      rect.right += width;
      OS.DrawThemeBackground(hTheme, memDC, BP_CHECKBOX, CBS_CHECKEDNORMAL, rect, null);
      rect.left += width;
      rect.right += width;
      OS.DrawThemeBackground(hTheme, memDC, BP_CHECKBOX, CBS_UNCHECKEDNORMAL, rect, null);
      rect.left += width;
      rect.right += width;
      OS.DrawThemeBackground(hTheme, memDC, BP_CHECKBOX, CBS_MIXEDNORMAL, rect, null);
      OS.CloseThemeData(hTheme);
    } else {
      OS.DrawFrameControl(memDC, rect, DFC_BUTTON, OS.DFCS_BUTTONCHECK | OS.DFCS_FLAT);
      rect.left += width;
      rect.right += width;
      OS.DrawFrameControl(
          memDC, rect, DFC_BUTTON, (OS.DFCS_BUTTONCHECK | OS.DFCS_CHECKED) | OS.DFCS_FLAT);
      rect.left += width;
      rect.right += width;
      OS.DrawFrameControl(
          memDC, rect, DFC_BUTTON, (OS.DFCS_BUTTONCHECK | OS.DFCS_INACTIVE) | OS.DFCS_FLAT);
      rect.left += width;
      rect.right += width;
      OS.DrawFrameControl(
          memDC,
          rect,
          DFC_BUTTON,
          ((OS.DFCS_BUTTONCHECK | OS.DFCS_CHECKED) | OS.DFCS_INACTIVE) | OS.DFCS_FLAT);
    }
    OS.SelectObject(memDC, hOldBitmap);
    OS.DeleteDC(memDC);
    OS.ReleaseDC(handle, hDC);
    if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
      OS.ImageList_Add(hNewStateList, hBitmap, 0);
    } else {
      OS.ImageList_AddMasked(hNewStateList, hBitmap, clrBackground);
    }
    OS.DeleteObject(hBitmap);
    int hOldStateList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_STATE, 0);
    OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_STATE, hNewStateList);
    if (hOldStateList != 0) {
      OS.ImageList_Destroy(hOldStateList);
    }
  }
}
