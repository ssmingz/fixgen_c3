class PlaceHold {
  public boolean eventPosted(EventObject event) {
    ElementSelectionEvent e = ((ElementSelectionEvent) (event));
    String text = _defText;
    ProjectProxy p = getContext().getProject();
    if (p != null) {
      ElementSelectionModel selections = p.getTreeSelectionModel();
      ACSTargetElement[] targets = selections.getSelectedTargets();
      if ((targets != null) && (targets.length > 0)) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < targets.length; i++) {
          buf.append(targets[i].getName());
          if (i < (targets.length - 1)) {
            buf.append(", ");
          }
        }
        text = buf.toString();
      }
    }
    setText(text);
    return true;
  }
}
