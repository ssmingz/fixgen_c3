class PlaceHold {
  void addRelation(Control control) {
    if (!control.isDescribedByLabel()) {
      return;
    }
    int accessible = OS.gtk_widget_get_accessible(labelHandle);
    int controlAccessible = OS.gtk_widget_get_accessible(control.handle);
    if ((accessible != 0) && (controlAccessible != 0)) {
      OS.atk_object_add_relationship(controlAccessible, ATK_RELATION_LABELLED_BY, accessible);
    }
  }
}
