class PlaceHold {
  public String open() {
    String fullPath = null;
    fileNames = new String[0];
    int method = 0;
    int methodImpl = 0;
    Callback callback = null;
    if ((style & SWT.SAVE) != 0) {
      NSSavePanel savePanel = NSSavePanel.savePanel();
      panel = savePanel;
      if (!overwrite) {
        callback = new Callback(this, "_overwriteExistingFileCheck", 3);
        int proc = callback.getAddress();
        if (proc == 0) {
          error(ERROR_NO_MORE_CALLBACKS);
        }
        method = OS.class_getInstanceMethod(class_NSSavePanel, sel_overwriteExistingFileCheck);
        if (method != 0) {
          methodImpl = OS.method_setImplementation(method, proc);
        }
      }
    } else {
      NSOpenPanel openPanel = NSOpenPanel.openPanel();
      openPanel.setAllowsMultipleSelection((style & SWT.MULTI) != 0);
      panel = openPanel;
    }
    panel.setCanCreateDirectories(true);
    int jniRef = 0;
    SWTPanelDelegate delegate = null;
    if ((filterExtensions != null) && (filterExtensions.length != 0)) {
      delegate = ((SWTPanelDelegate) (new SWTPanelDelegate().alloc().init()));
      jniRef = OS.NewGlobalRef(this);
      if (jniRef == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      OS.object_setInstanceVariable(delegate.id, SWT_OBJECT, jniRef);
      panel.setDelegate(delegate);
      NSPopUpButton widget = ((NSPopUpButton) (new NSPopUpButton().alloc()));
      widget.initWithFrame(new NSRect(), false);
      widget.setTarget(delegate);
      widget.setAction(sel_sendSelection_);
      NSMenu menu = widget.menu();
      menu.setAutoenablesItems(false);
      for (int i = 0; i < filterExtensions.length; i++) {
        String str = filterExtensions[i];
        if ((filterNames != null) && (filterNames.length > i)) {
          str = filterNames[i];
        }
        NSMenuItem nsItem = ((NSMenuItem) (new NSMenuItem().alloc()));
        nsItem.initWithTitle(NSString.stringWith(str), 0, NSString.stringWith(""));
        menu.addItem(nsItem);
        nsItem.release();
      }
      widget.selectItemAtIndex(
          (0 <= filterIndex) && (filterIndex < filterExtensions.length) ? filterIndex : 0);
      widget.sizeToFit();
      panel.setAccessoryView(widget);
      popup = widget;
    }
    panel.setTitle(NSString.stringWith(title != null ? title : ""));
    NSApplication application = NSApplication.sharedApplication();
    if ((parent != null) && ((style & SWT.SHEET) != 0)) {
      application.beginSheet(panel, window, null, 0, 0);
    }
    NSString dir = (filterPath != null) ? NSString.stringWith(filterPath) : null;
    NSString file = (fileName != null) ? NSString.stringWith(fileName) : null;
    int response = panel.runModalForDirectory(dir, file);
    if ((parent != null) && ((style & SWT.SHEET) != 0)) {
      application.endSheet(panel, 0);
    }
    if (!overwrite) {
      if (method != 0) {
        OS.method_setImplementation(method, methodImpl);
      }
      callback.dispose();
    }
    if (response == OS.NSFileHandlingPanelOKButton) {
      NSString filename = panel.filename();
      fullPath = filename.getString();
      if ((style & SWT.SAVE) == 0) {
        NSArray filenames = ((NSOpenPanel) (panel)).filenames();
        int count = ((int) (filenames.count()));
        fileNames = new String[count];
        for (int i = 0; i < count; i++) {
          filename = new NSString(filenames.objectAtIndex(i));
          NSString filenameOnly = filename.lastPathComponent();
          NSString pathOnly = filename.stringByDeletingLastPathComponent();
          if (i == 0) {
            filterPath = pathOnly.getString();
            fileName = fileNames[0] = filenameOnly.getString();
          } else if (pathOnly.getString().equals(filterPath)) {
            fileNames[i] = filenameOnly.getString();
          } else {
            fileNames[i] = filename.getString();
          }
        }
      }
      filterIndex = -1;
    }
    if (popup != null) {
      filterIndex = ((int) (popup.indexOfSelectedItem()));
      panel.setAccessoryView(null);
      popup.release();
      popup = null;
    }
    if (delegate != null) {
      panel.setDelegate(null);
      delegate.release();
    }
    if (jniRef != 0) {
      OS.DeleteGlobalRef(jniRef);
    }
    panel = null;
    return fullPath;
  }
}
