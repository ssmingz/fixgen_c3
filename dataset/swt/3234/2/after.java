class PlaceHold {
  static boolean kde_init() {
    if (true) {
      return false;
    }
    try {
      Library.loadLibrary("swt-kde");
    } catch (Throwable e) {
      return false;
    }
    byte[] nameBuffer = Converter.wcsToMbcs(null, "SWT", true);
    int qcString = KDE.QCString_new(nameBuffer);
    KDE.KApplication_new(qcString);
    KDE.QCString_delete(qcString);
    return true;
  }
}
