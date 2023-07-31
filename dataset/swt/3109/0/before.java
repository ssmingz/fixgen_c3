class PlaceHold {
  int get_windowHandle(int pWindowHandle) {
    COM.MoveMemory(pWindowHandle, new int[] {control.handle}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
