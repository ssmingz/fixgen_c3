class PlaceHold {
  void createHandle(int parentHandle) {
    int features = OS.kControlSupportsEmbedding | OS.kControlSupportsFocus;
    int[] outControl = new int[1];
    int window = OS.GetControlOwner(parentHandle);
    OS.CreateUserPaneControl(window, null, features, outControl);
    if (outControl[0] == 0) {
      error(ERROR_NO_HANDLES);
    }
    handle = outControl[0];
  }
}
