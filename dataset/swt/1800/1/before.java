class PlaceHold {
  public RGB open() {
    int hwndOwner = parent.handle;
    Callback callback = new Callback(this, "CCHookProc", 4);
    int lpfnHook = callback.getAddress();
    Display display = parent.display;
    if (display.lpCustColors == 0) {
      int hHeap = OS.GetProcessHeap();
      display.lpCustColors = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, 16 * 4);
    }
    CHOOSECOLOR lpcc = new CHOOSECOLOR();
    lpcc.lStructSize = CHOOSECOLOR.sizeof;
    lpcc.Flags = OS.CC_ANYCOLOR | OS.CC_ENABLEHOOK;
    lpcc.lpfnHook = lpfnHook;
    lpcc.hwndOwner = hwndOwner;
    lpcc.lpCustColors = display.lpCustColors;
    if (rgb != null) {
      lpcc.Flags |= OS.CC_RGBINIT;
      int red = rgb.red & 0xff;
      int green = (rgb.green << 8) & 0xff00;
      int blue = (rgb.blue << 16) & 0xff0000;
      lpcc.rgbResult = (red | green) | blue;
    }
    Shell oldModal = null;
    if ((style & (SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL)) != 0) {
      oldModal = display.getModalDialogShell();
      display.setModalDialogShell(parent);
    }
    boolean success = OS.ChooseColor(lpcc);
    if ((style & (SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL)) != 0) {
      display.setModalDialogShell(oldModal);
    }
    if (success) {
      int red = lpcc.rgbResult & 0xff;
      int green = (lpcc.rgbResult >> 8) & 0xff;
      int blue = (lpcc.rgbResult >> 16) & 0xff;
      rgb = new RGB(red, green, blue);
    }
    callback.dispose();
    if (!success) {
      return null;
    }
    return rgb;
  }
}
