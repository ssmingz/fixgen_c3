class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    this.expanded = expanded;
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      OS.gtk_expander_set_expanded(handle, expanded);
      parent.layoutItems(0, true);
    } else {
      parent.showItem(parent.indexOf(this));
    }
  }
}
