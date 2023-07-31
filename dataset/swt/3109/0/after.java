class PlaceHold {
  int get_windowHandle(int pWindowHandle) {
    if (DEBUG) {
      print(
          ((this + ".IAccessible2::get_windowHandle returning ") + control.handle) + hresult(S_OK));
    }
    COM.MoveMemory(pWindowHandle, new int[] {control.handle}, PTR_SIZEOF);
    return COM.S_OK;
  }
}
