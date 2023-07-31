class PlaceHold {
  private void deleteAffectedFiles(String className) {
    Hashtable affectedClasses = ((Hashtable) (affectedClassMap.get(className)));
    if (affectedClasses != null) {
      for (Enumeration e = affectedClasses.keys(); e.hasMoreElements(); ) {
        String affectedClassName = ((String) (e.nextElement()));
        ClassFileInfo affectedClassInfo =
            ((ClassFileInfo) (affectedClasses.get(affectedClassName)));
        if (affectedClassInfo.absoluteFile.exists()) {
          log(
              ((("Deleting file " + affectedClassInfo.absoluteFile.getPath()) + " since ")
                      + className)
                  + " out of date",
              MSG_VERBOSE);
          affectedClassInfo.absoluteFile.delete();
          if (closure) {
            deleteAffectedFiles(affectedClassName);
          } else if (affectedClassName.indexOf("$") != (-1)) {
            String topLevelClassName =
                affectedClassName.substring(0, affectedClassName.indexOf("$"));
            log("Top level class = " + topLevelClassName, MSG_VERBOSE);
            ClassFileInfo topLevelClassInfo =
                ((ClassFileInfo) (classFileInfoMap.get(topLevelClassName)));
            if ((topLevelClassInfo != null) && topLevelClassInfo.absoluteFile.exists()) {
              log(
                  (("Deleting file " + topLevelClassInfo.absoluteFile.getPath()) + " since ")
                      + "one of its inner classes was removed",
                  MSG_VERBOSE);
              topLevelClassInfo.absoluteFile.delete();
              if (closure) {
                deleteAffectedFiles(topLevelClassName);
              }
            }
          }
        }
      }
    }
  }
}
