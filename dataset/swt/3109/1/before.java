class PlaceHold {
  int get_nRelations(int pNRelations) {
    int count = getRelationCount();
    COM.MoveMemory(pNRelations, new int[] {count}, 4);
    return COM.S_OK;
  }
}
