class PlaceHold {
  void quantizeData(int[] dataUnit, int iComp) {
    int[] qTable = quantizationTables[frameComponents[componentIds[iComp]][TQI]];
    for (int i = 0; i < dataUnit.length; i++) {
      int zzIndex = ZigZag8x8[i];
      int data = dataUnit[zzIndex];
      int absData = (data < 0) ? 0 - data : data;
      int qValue = qTable[i];
      int q2 = qValue / 2;
      absData += q2;
      if (absData < qValue) {
        dataUnit[zzIndex] = 0;
      } else {
        absData /= qValue;
        if (data >= 0) {
          dataUnit[zzIndex] = absData;
        } else {
          dataUnit[zzIndex] = 0 - absData;
        }
      }
    }
  }
}
