class PlaceHold {
  int PromptForSaveToFile(int arg0, int arg1, int arg2, int arg3, int arg4) {
    int aDefaultFile;
    int aSuggestedFileExtension;
    int _retval;
    boolean hasLauncher = false;
    boolean using_1_8 = false;
    boolean using_1_9 = false;
    nsISupports support = new nsISupports(arg0);
    int[] result = new int[1];
    int rc = support.QueryInterface(NS_IHELPERAPPLAUNCHER_IID, result);
    if (rc == 0) {
      using_1_8 = true;
      hasLauncher = true;
      new nsISupports(result[0]).Release();
    } else {
      result[0] = 0;
      rc = support.QueryInterface(NS_IHELPERAPPLAUNCHER_IID, result);
      if (rc == 0) {
        using_1_9 = true;
        hasLauncher = true;
        new nsISupports(result[0]).Release();
      } else {
        result[0] = 0;
        rc = support.QueryInterface(NS_IHELPERAPPLAUNCHER_IID, result);
        if (rc == 0) {
          hasLauncher = true;
          new nsISupports(result[0]).Release();
        }
      }
    }
    result[0] = 0;
    if (hasLauncher) {
      aDefaultFile = arg2;
      aSuggestedFileExtension = arg3;
      _retval = arg4;
    } else {
      aDefaultFile = arg1;
      aSuggestedFileExtension = arg2;
      _retval = arg3;
    }
    int length = XPCOM.strlen_PRUnichar(aDefaultFile);
    char[] dest = new char[length];
    XPCOM.memmove(dest, aDefaultFile, length * 2);
    String defaultFile = new String(dest);
    length = XPCOM.strlen_PRUnichar(aSuggestedFileExtension);
    dest = new char[length];
    XPCOM.memmove(dest, aSuggestedFileExtension, length * 2);
    String suggestedFileExtension = new String(dest);
    Shell shell = new Shell();
    FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
    fileDialog.setFileName(defaultFile);
    fileDialog.setFilterExtensions(new String[] {suggestedFileExtension});
    String name = fileDialog.open();
    shell.close();
    if (name == null) {
      if (hasLauncher) {
        if (using_1_8) {
          nsIHelperAppLauncher_1_8 launcher = new nsIHelperAppLauncher_1_8(arg0);
          rc = launcher.Cancel(NS_BINDING_ABORTED);
        } else if (using_1_9) {
          nsIHelperAppLauncher_1_9 launcher = new nsIHelperAppLauncher_1_9(arg0);
          rc = launcher.Cancel(NS_BINDING_ABORTED);
        } else {
          nsIHelperAppLauncher launcher = new nsIHelperAppLauncher(arg0);
          rc = launcher.Cancel();
        }
        if (rc != XPCOM.NS_OK) {
          Mozilla.error(rc);
        }
        return XPCOM.NS_OK;
      }
      return XPCOM.NS_ERROR_FAILURE;
    }
    nsEmbedString path = new nsEmbedString(name);
    rc = XPCOM.NS_NewLocalFile(path.getAddress(), true, result);
    path.dispose();
    if (rc != XPCOM.NS_OK) {
      Mozilla.error(rc);
    }
    if (result[0] == 0) {
      Mozilla.error(NS_ERROR_NULL_POINTER);
    }
    XPCOM.memmove(_retval, result, PTR_SIZEOF);
    return XPCOM.NS_OK;
  }
}
