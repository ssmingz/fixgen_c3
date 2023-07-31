class PlaceHold {
  void setBounds(int x, int y, int width, int height, int flags, boolean defer) {
    if (findImageControl() != null) {
      if (backgroundImage == null) {
        flags |= OS.SWP_NOCOPYBITS;
      }
    } else if (OS.GetWindow(handle, GW_CHILD) == 0) {
      if ((OS.COMCTL32_MAJOR >= 6) && OS.IsAppThemed()) {
        if (findThemeControl() != null) {
          flags |= OS.SWP_NOCOPYBITS;
        }
      }
    }
    long topHandle = topHandle();
    if (defer && (parent != null)) {
      forceResize();
      if (parent.lpwp != null) {
        int index = 0;
        WINDOWPOS[] lpwp = parent.lpwp;
        while (index < lpwp.length) {
          if (lpwp[index] == null) {
            break;
          }
          index++;
        }
        if (index == lpwp.length) {
          WINDOWPOS[] newLpwp = new WINDOWPOS[lpwp.length + 4];
          System.arraycopy(lpwp, 0, newLpwp, 0, lpwp.length);
          parent.lpwp = lpwp = newLpwp;
        }
        WINDOWPOS wp = new WINDOWPOS();
        wp.hwnd = topHandle;
        wp.x = x;
        wp.y = y;
        wp.cx = width;
        wp.cy = height;
        wp.flags = flags;
        lpwp[index] = wp;
        return;
      }
    }
    SetWindowPos(topHandle, 0, x, y, width, height, flags);
  }
}
