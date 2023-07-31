class PlaceHold {
  LRESULT WM_SYSCOMMAND(int wParam, int lParam) {
    if ((wParam & 0xf000) == 0) {
      Decorations shell = menuShell();
      if (shell.isEnabled()) {
        MenuItem item = shell.findMenuItem(wParam & 0xffff);
        if (item != null) {
          item.wmCommandChild(wParam, lParam);
        }
      }
      return LRESULT.ZERO;
    }
    int cmd = wParam & 0xfff0;
    switch (cmd) {
      case OS.SC_CLOSE:
        int hwndShell = menuShell().handle;
        int bits = OS.GetWindowLong(hwndShell, GWL_STYLE);
        if ((bits & OS.WS_SYSMENU) == 0) {
          return LRESULT.ZERO;
        }
        break;
      case OS.SC_KEYMENU:
        if (hooks(KeyDown) || hooks(KeyUp)) {
          Decorations shell = menuShell();
          Menu menu = shell.getMenuBar();
          if (menu != null) {
            char key = mbcsToWcs(lParam);
            if (key != 0) {
              key = Character.toUpperCase(key);
              MenuItem[] items = menu.getItems();
              for (int i = 0; i < items.length; i++) {
                MenuItem item = items[i];
                String text = item.getText();
                char mnemonic = findMnemonic(text);
                if ((text.length() > 0) && (mnemonic == 0)) {
                  char ch = text.charAt(0);
                  if (Character.toUpperCase(ch) == key) {
                    Display display = getDisplay();
                    display.mnemonicKeyHit = false;
                    return LRESULT.ZERO;
                  }
                }
              }
            }
          }
        }
      case OS.SC_HSCROLL:
      case OS.SC_VSCROLL:
        Decorations shell = menuShell();
        if ((!shell.isEnabled()) || (!shell.isActive())) {
          return LRESULT.ZERO;
        }
        break;
      case OS.SC_MINIMIZE:
        menuShell().saveFocus();
        break;
    }
    return null;
  }
}
