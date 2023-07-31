class Clipboard {
  public Clipboard(Display display) {
    checkSubclass();
    if (display == null) {
      display = Display.getCurrent();
      if (display == null) {
        display = Display.getDefault();
      }
    }
    if (display.getThread() != Thread.currentThread()) {
      DND.error(ERROR_THREAD_INVALID_ACCESS);
    }
    this.display = display;
    TCHAR chFormatName = new TCHAR(0, "Preferred DropEffect", true);
    CFSTR_PREFERREDDROPEFFECT = COM.RegisterClipboardFormat(chFormatName);
    createCOMInterfaces();
    this.AddRef();
  }
}
