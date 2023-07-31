class PlaceHold {
  static void loadAdditionalLibraries(String mozillaPath, boolean isGlued) {
    String libName = null;
    if (!isGlued) {
      if (IsXULRunner24 == null) {
        IsXULRunner24 = (new File(mozillaPath, LIB_XPCOM).exists()) ? Boolean.FALSE : Boolean.TRUE;
      }
      if (IsXULRunner24.booleanValue()) {
        libName = LIB_FIX_XULRUNNER24;
      }
    } else if (MozillaVersion.CheckVersion(VERSION_XR10, true)) {
      libName = LIB_FIX_XULRUNNER10;
    }
    if (libName == null) {
      return;
    }
    File libsDir =
        new File((((getProfilePath() + "/libs/") + Mozilla.OS()) + '/') + Mozilla.Arch());
    File file = new File(libsDir, libName);
    if (!file.exists()) {
      InputStream is = Library.class.getResourceAsStream('/' + libName);
      if (is != null) {
        if (!libsDir.exists()) {
          libsDir.mkdirs();
        }
        int read;
        byte[] buffer = new byte[4096];
        try {
          FileOutputStream os = new FileOutputStream(file);
          while ((read = is.read(buffer)) != (-1)) {
            os.write(buffer, 0, read);
          }
          os.close();
          is.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
      }
    }
    if (file.exists()) {
      byte[] bytes = Converter.wcsToMbcs(null, file.getAbsolutePath(), true);
      OS.dlopen(bytes, OS.RTLD_NOW | OS.RTLD_GLOBAL);
    }
  }
}
