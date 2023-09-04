class PlaceHold {
  public TypeBinding resolveType(BlockScope scope) {
    boolean expressionIsCast;
    if ((expressionIsCast = this.expression instanceof CastExpression) == true)
      this.expression.bits |= IgnoreNeedForCastCheckMASK;
    // will check later on

    TypeBinding expressionType = this.expression.resolveType(scope);
    if (expressionType == null) {
      this.constant = NotAConstant;
      return null;
    }
    int expressionTypeID = expressionType.id;
    // autoboxing support
    boolean use15specifics = scope.compilerOptions().sourceLevel >= JDK1_5;
    if (use15specifics) {
      if (!expressionType.isBaseType()) {
        expressionTypeID = scope.environment().computeBoxingType(expressionType).id;
      }
    }
    if (expressionTypeID > 15) {
      this.constant = NotAConstant;
      scope.problemReporter().invalidOperator(this, expressionType);
      return null;
    }
    int tableId;
    switch ((bits & OperatorMASK) >> OperatorSHIFT) {
      case NOT:
        tableId = AND_AND;
        break;
      case TWIDDLE:
        tableId = LEFT_SHIFT;
        break;
      default:
        tableId = MINUS;
    } // + and - cases

    // the code is an int
    // (cast)  left   Op (cast)  rigth --> result
    // 0000   0000       0000   0000      0000
    // <<16   <<12       <<8    <<4       <<0
    int operatorSignature = OperatorSignatures[tableId][(expressionTypeID << 4) + expressionTypeID];
    this.expression.computeConversion(
        scope, TypeBinding.wellKnownType(scope, (operatorSignature >>> 16) & 0xf), expressionType);
    this.bits |= operatorSignature & 0xf;
    switch (operatorSignature & 0xf) {
        // only switch on possible result type.....
      case T_boolean:
        this.resolvedType = BooleanBinding;
        break;
      case T_byte:
        this.resolvedType = ByteBinding;
        break;
      case T_char:
        this.resolvedType = CharBinding;
        break;
      case T_double:
        this.resolvedType = DoubleBinding;
        break;
      case T_float:
        this.resolvedType = FloatBinding;
        break;
      case T_int:
        this.resolvedType = IntBinding;
        break;
      case T_long:
        this.resolvedType = LongBinding;
        break;
      default:
        // error........
        this.constant = Constant.NotAConstant;
        if (expressionTypeID != T_undefined)
          scope.problemReporter().invalidOperator(this, expressionType);

        return null;
    }
    // compute the constant when valid
    if (this.expression.constant != Constant.NotAConstant) {
      this.constant =
          Constant.computeConstantOperation(
              this.expression.constant, expressionTypeID, (bits & OperatorMASK) >> OperatorSHIFT);
    } else {
      this.constant = Constant.NotAConstant;
      if (((bits & OperatorMASK) >> OperatorSHIFT) == NOT) {
        Constant cst = expression.optimizedBooleanConstant();
        if (cst != Constant.NotAConstant)
          this.optimizedBooleanConstant = Constant.fromValue(!cst.booleanValue());
      }
    }
    if (expressionIsCast) {
      // check need for operand cast
      CastExpression.checkNeedForArgumentCast(
          scope, tableId, operatorSignature, this.expression, expressionTypeID);
    }
    return this.resolvedType;
  }
}
