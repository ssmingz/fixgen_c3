class PlaceHold {
  public String open() {
    String fullPath = null;
    fileNames = new String[0];
    int titlePtr = 0;
    if (title != null) {
      char[] buffer = new char[title.length()];
      title.getChars(0, buffer.length, buffer, 0);
      titlePtr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
    }
    int fileNamePtr = 0;
    if (fileName != null) {
      char[] buffer = new char[fileName.length()];
      fileName.getChars(0, buffer.length, buffer, 0);
      fileNamePtr = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
    }
    NavDialogCreationOptions options = new NavDialogCreationOptions();
    options.windowTitle = options.clientName = titlePtr;
    options.parentWindow = OS.GetControlOwner(handle);
    options.optionFlags = OS.kNavSupportPackages | OS.kNavAllowInvisibleFiles;
    options.location_h = -1;
    options.location_v = -1;
    options.saveFileName = fileNamePtr;
    int identifiers = 0;
    int kUTTypeData = 0;
    int[] outDialog = new int[1];
    if ((style & SWT.SAVE) != 0) {
      OS.NavCreatePutFileDialog(options, 0, 0, 0, 0, outDialog);
    } else {
      if ((style & SWT.MULTI) != 0) {
        options.optionFlags |= OS.kNavAllowMultipleFiles;
      }
      OS.NavCreateGetFileDialog(options, 0, 0, 0, 0, 0, outDialog);
    }
    if (outDialog[0] != 0) {
      if (filterExtensions == null) {
        filterExtensions = new String[0];
      }
      if ((false && (filterExtensions.length != 0)) && (OS.VERSION >= 0x1040)) {
        int count = 0;
        String[] extensions = new String[filterExtensions.length];
        for (int i = 0; i < filterExtensions.length; i++) {
          if (filterExtensions[i] != null) {
            int start = 0;
            int length = filterExtensions[i].length();
            while (start < length) {
              int index = filterExtensions[i].indexOf(EXTENSION_SEPARATOR, start);
              if (index == (-1)) {
                index = length;
              }
              String extension = filterExtensions[i].substring(start, index);
              boolean found = false;
              for (int j = 0; j < count; j++) {
                if (extensions[j].equals(extension)) {
                  found = true;
                  break;
                }
              }
              if (!found) {
                if (count == extensions.length) {
                  String[] newExtensions = new String[count + 4];
                  System.arraycopy(extensions, 0, newExtensions, 0, count);
                  extensions = newExtensions;
                }
                extensions[count++] = extension;
              }
              start = index + 1;
            }
          }
        }
        String publicData = "public.data";
        char[] buffer = new char[publicData.length()];
        publicData.getChars(0, buffer.length, buffer, 0);
        kUTTypeData = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
        identifiers = OS.CFArrayCreateMutable(kCFAllocatorDefault, 0, 0);
        for (int i = 0; i < count; i++) {
          int uti;
          String extension = extensions[i];
          if (extension.equals("*.*")) {
            String publicItem = "public.item";
            buffer = new char[publicItem.length()];
            publicItem.getChars(0, buffer.length, buffer, 0);
            uti = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
          } else {
            boolean star = extension.startsWith("*.");
            buffer = new char[extension.length() - (star ? 2 : 0)];
            extension.getChars(star ? 2 : 0, extension.length(), buffer, 0);
            int ext = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
            uti =
                OS.UTTypeCreatePreferredIdentifierForTag(
                    OS.kUTTagClassFilenameExtension(), ext, kUTTypeData);
            OS.CFRelease(ext);
          }
          if (uti != 0) {
            OS.CFArrayAppendValue(identifiers, uti);
          }
        }
        OS.NavDialogSetFilterTypeIdentifiers(outDialog[0], identifiers);
      }
      OS.NavDialogRun(outDialog[0]);
      int action = OS.NavDialogGetUserAction(outDialog[0]);
      switch (action) {
        case OS.kNavUserActionOpen:
        case OS.kNavUserActionChoose:
        case OS.kNavUserActionSaveAs:
          {
            NavReplyRecord record = new NavReplyRecord();
            OS.NavDialogGetReply(outDialog[0], record);
            AEDesc selection = new AEDesc();
            selection.descriptorType = record.selection_descriptorType;
            selection.dataHandle = record.selection_dataHandle;
            int[] count = new int[1];
            OS.AECountItems(selection, count);
            if (count[0] > 0) {
              fileNames = new String[count[0]];
              int maximumSize = 80;
              int dataPtr = OS.NewPtr(maximumSize);
              int[] aeKeyword = new int[1];
              int[] typeCode = new int[1];
              int[] actualSize = new int[1];
              int pathString = 0;
              int fullString = 0;
              int fileString = 0;
              if ((style & SWT.SAVE) != 0) {
                if (OS.AEGetNthPtr(
                        selection,
                        1,
                        typeFSRef,
                        aeKeyword,
                        typeCode,
                        dataPtr,
                        maximumSize,
                        actualSize)
                    == OS.noErr) {
                  byte[] fsRef = new byte[actualSize[0]];
                  OS.memmove(fsRef, dataPtr, actualSize[0]);
                  int pathUrl = OS.CFURLCreateFromFSRef(kCFAllocatorDefault, fsRef);
                  int fullUrl =
                      OS.CFURLCreateCopyAppendingPathComponent(
                          kCFAllocatorDefault, pathUrl, record.saveFileName, false);
                  pathString = OS.CFURLCopyFileSystemPath(pathUrl, kCFURLPOSIXPathStyle);
                  fullString = OS.CFURLCopyFileSystemPath(fullUrl, kCFURLPOSIXPathStyle);
                  fileString = record.saveFileName;
                  OS.CFRelease(pathUrl);
                  OS.CFRelease(fullUrl);
                }
              } else {
                for (int i = 0; i < count[0]; i++) {
                  if (OS.AEGetNthPtr(
                          selection,
                          i + 1,
                          typeFSRef,
                          aeKeyword,
                          typeCode,
                          dataPtr,
                          maximumSize,
                          actualSize)
                      == OS.noErr) {
                    byte[] fsRef = new byte[actualSize[0]];
                    OS.memmove(fsRef, dataPtr, actualSize[0]);
                    int url = OS.CFURLCreateFromFSRef(kCFAllocatorDefault, fsRef);
                    if (i == 0) {
                      int pathUrl =
                          OS.CFURLCreateCopyDeletingLastPathComponent(kCFAllocatorDefault, url);
                      pathString = OS.CFURLCopyFileSystemPath(pathUrl, kCFURLPOSIXPathStyle);
                      fullString = OS.CFURLCopyFileSystemPath(url, kCFURLPOSIXPathStyle);
                      fileString = OS.CFURLCopyLastPathComponent(url);
                      OS.CFRelease(pathUrl);
                    } else {
                      int lastString = OS.CFURLCopyLastPathComponent(url);
                      int length = OS.CFStringGetLength(lastString);
                      char[] buffer = new char[length];
                      CFRange range = new CFRange();
                      range.length = length;
                      OS.CFStringGetCharacters(lastString, range, buffer);
                      fileNames[i] = new String(buffer);
                      OS.CFRelease(lastString);
                    }
                    OS.CFRelease(url);
                  }
                }
              }
              OS.DisposePtr(dataPtr);
              if (pathString != 0) {
                int length = OS.CFStringGetLength(pathString);
                char[] buffer = new char[length];
                CFRange range = new CFRange();
                range.length = length;
                OS.CFStringGetCharacters(pathString, range, buffer);
                OS.CFRelease(pathString);
                filterPath = new String(buffer);
              }
              if (fullString != 0) {
                int length = OS.CFStringGetLength(fullString);
                char[] buffer = new char[length];
                CFRange range = new CFRange();
                range.length = length;
                OS.CFStringGetCharacters(fullString, range, buffer);
                OS.CFRelease(fullString);
                fullPath = new String(buffer);
              }
              if (fileString != 0) {
                int length = OS.CFStringGetLength(fileString);
                char[] buffer = new char[length];
                CFRange range = new CFRange();
                range.length = length;
                OS.CFStringGetCharacters(fileString, range, buffer);
                OS.CFRelease(fileString);
                fileName = fileNames[0] = new String(buffer);
              }
            }
          }
      }
    }
    if (titlePtr != 0) {
      OS.CFRelease(titlePtr);
    }
    if (fileNamePtr != 0) {
      OS.CFRelease(fileNamePtr);
    }
    if (outDialog[0] != 0) {
      OS.NavDialogDispose(outDialog[0]);
    }
    if (identifiers != 0) {
      int count = OS.CFArrayGetCount(identifiers);
      for (int i = 0; i < count; i++) {
        OS.CFRelease(OS.CFArrayGetValueAtIndex(identifiers, i));
      }
      OS.CFRelease(identifiers);
    }
    if (kUTTypeData != 0) {
      OS.CFRelease(kUTTypeData);
    }
    return fullPath;
  }
}
