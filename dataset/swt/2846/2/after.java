class PlaceHold {
  int get_accHelpTopic(int pszHelpFile, int varChild, int pidTopic) {
    if (DEBUG) {
      print(this + ".IAccessible::get_accHelpTopic");
    }
    int code = COM.DISP_E_MEMBERNOTFOUND;
    if (iaccessible != null) {
      code = iaccessible.get_accHelpTopic(pszHelpFile, varChild, pidTopic);
      if (code == COM.E_INVALIDARG) {
        code = COM.DISP_E_MEMBERNOTFOUND;
      }
    }
    return code;
  }
}
