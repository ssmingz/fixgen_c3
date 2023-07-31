class PlaceHold {
  boolean translateTraversal(MSG msg) {
    switch (msg.wParam) {
      case OS.VK_RETURN:
      case OS.VK_ESCAPE:
        if (OS.SendMessage(handle, CB_GETDROPPEDSTATE, 0, 0) != 0) {
          return false;
        }
    }
    return super.translateTraversal(msg);
  }
}
