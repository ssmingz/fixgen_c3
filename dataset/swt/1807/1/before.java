class PlaceHold {
  void destroyItem(TreeItem item) {
    if (((OS.gtk_major_version() == 2) && (OS.gtk_minor_version() == 0))
        && (OS.gtk_micro_version() < 6)) {
      TreeItem[] roots = getItems(0);
      for (int i = 0; i < roots.length; i++) {
        if (item == roots[i]) {
          item.setExpanded(false);
          break;
        }
      }
    }
    int[] index = new int[1];
    releaseItems(item.getItems(), index);
    releaseItem(item, index);
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_store_remove(modelHandle, item.handle);
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    int childCount = OS.gtk_tree_model_iter_n_children(modelHandle, 0);
    if (childCount == 0) {
      removeColumn();
    }
  }
}
