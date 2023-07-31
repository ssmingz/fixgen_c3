class PlaceHold {
  private void scandir(
      File dir,
      TokenizedPath path,
      boolean fast,
      String[] newfiles,
      LinkedList directoryNamesFollowed) {
    String vpath = path.toString();
    if ((vpath.length() > 0) && (!vpath.endsWith(File.separator))) {
      vpath += File.separator;
    }
    if (fast && hasBeenScanned(vpath)) {
      return;
    }
    if (!followSymlinks) {
      ArrayList noLinks = new ArrayList();
      for (int i = 0; i < newfiles.length; i++) {
        try {
          if (FILE_UTILS.isSymbolicLink(dir, newfiles[i])) {
            String name = vpath + newfiles[i];
            File file = new File(dir, newfiles[i]);
            (file.isDirectory() ? dirsExcluded : filesExcluded).addElement(name);
          } else {
            noLinks.add(newfiles[i]);
          }
        } catch (IOException ioe) {
          String msg =
              "IOException caught while checking " + "for links, couldn't get canonical path!";
          System.err.println(msg);
          noLinks.add(newfiles[i]);
        }
      }
      newfiles = ((String[]) (noLinks.toArray(new String[noLinks.size()])));
    } else {
      directoryNamesFollowed.addFirst(dir.getName());
    }
    for (int i = 0; i < newfiles.length; i++) {
      String name = vpath + newfiles[i];
      TokenizedPath newPath = new TokenizedPath(path, newfiles[i]);
      File file = new File(dir, newfiles[i]);
      String[] children = file.list();
      if (children == null) {
        if (isIncluded(newPath)) {
          accountForIncludedFile(newPath, file);
        } else {
          everythingIncluded = false;
          filesNotIncluded.addElement(name);
        }
      } else {
        if (followSymlinks && causesIllegalSymlinkLoop(newfiles[i], dir, directoryNamesFollowed)) {
          System.err.println(
              (("skipping symbolic link " + file.getAbsolutePath())
                      + " -- too many levels of symbolic")
                  + " links.");
          continue;
        }
        if (isIncluded(newPath)) {
          accountForIncludedDir(newPath, file, fast, children, directoryNamesFollowed);
        } else {
          everythingIncluded = false;
          dirsNotIncluded.addElement(name);
          if (fast && couldHoldIncluded(newPath)) {
            scandir(file, newPath, fast, children, directoryNamesFollowed);
          }
        }
        if (!fast) {
          scandir(file, newPath, fast, children, directoryNamesFollowed);
        }
      }
    }
    if (followSymlinks) {
      directoryNamesFollowed.removeFirst();
    }
  }
}
