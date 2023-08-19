class PlaceHold {
  public TypeBinding resolveType(BlockScope scope) {
    boolean leftIsCast;
    boolean rightIsCast;
    if ((leftIsCast = left instanceof CastExpression) == true)
      left.bits |= DisableUnnecessaryCastCheck;
    // will check later on

    TypeBinding leftType = left.resolveType(scope);
    if ((rightIsCast = right instanceof CastExpression) == true)
      right.bits |= DisableUnnecessaryCastCheck;
    // will check later on

    TypeBinding rightType = right.resolveType(scope);
    // use the id of the type to navigate into the table
    if ((leftType == null) || (rightType == null)) {
      constant = Constant.NotAConstant;
      return null;
    }
    int leftTypeID = leftType.id;
    int rightTypeID = rightType.id;
    // autoboxing support
    boolean use15specifics = scope.compilerOptions().sourceLevel >= JDK1_5;
    if (use15specifics) {
      if (((!leftType.isBaseType()) && (rightTypeID != T_JavaLangString))
          && (rightTypeID != T_null)) {
        leftTypeID = scope.environment().computeBoxingType(leftType).id;
      }
      if (((!rightType.isBaseType()) && (leftTypeID != T_JavaLangString))
          && (leftTypeID != T_null)) {
        rightTypeID = scope.environment().computeBoxingType(rightType).id;
      }
    }
    if ((leftTypeID > 15) || (rightTypeID > 15)) {
      // must convert String + Object || Object + String
      if (leftTypeID == T_JavaLangString) {
        rightTypeID = T_JavaLangObject;
      } else if (rightTypeID == T_JavaLangString) {
        leftTypeID = T_JavaLangObject;
      } else {
        constant = Constant.NotAConstant;
        scope.problemReporter().invalidOperator(this, leftType, rightType);
        return null;
      }
    }
    if (((bits & OperatorMASK) >> OperatorSHIFT) == PLUS) {
      if (leftTypeID == T_JavaLangString) {
        this.left.computeConversion(scope, leftType, leftType);
        if (rightType.isArrayType()
            && (((ArrayBinding) (rightType)).elementsType() == CharBinding)) {
          scope.problemReporter().signalNoImplicitStringConversionForCharArrayExpression(right);
        }
      }
      if (rightTypeID == T_JavaLangString) {
        this.right.computeConversion(scope, rightType, rightType);
        if (leftType.isArrayType() && (((ArrayBinding) (leftType)).elementsType() == CharBinding)) {
          scope.problemReporter().signalNoImplicitStringConversionForCharArrayExpression(left);
        }
      }
    }
    // the code is an int
    // (cast)  left   Op (cast)  right --> result
    // 0000   0000       0000   0000      0000
    // <<16   <<12       <<8    <<4       <<0
    // Don't test for result = 0. If it is zero, some more work is done.
    // On the one hand when it is not zero (correct code) we avoid doing the test
    int operator = (bits & OperatorMASK) >> OperatorSHIFT;
    int operatorSignature = OperatorSignatures[operator][(leftTypeID << 4) + rightTypeID];
    left.computeConversion(
        scope, TypeBinding.wellKnownType(scope, (operatorSignature >>> 16) & 0xf), leftType);
    right.computeConversion(
        scope, TypeBinding.wellKnownType(scope, (operatorSignature >>> 8) & 0xf), rightType);
    bits |= operatorSignature & 0xf;
    switch (operatorSignature & 0xf) {
        // record the current ReturnTypeID
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
      case T_JavaLangString:
        this.resolvedType = scope.getJavaLangString();
        break;
      default:
        // error........
        constant = Constant.NotAConstant;
        scope.problemReporter().invalidOperator(this, leftType, rightType);
        return null;
    }
    // check need for operand cast
    if (leftIsCast || rightIsCast) {
      CastExpression.checkNeedForArgumentCasts(
          scope,
          operator,
          operatorSignature,
          left,
          leftTypeID,
          leftIsCast,
          right,
          rightTypeID,
          rightIsCast);
    }
    // compute the constant when valid
    computeConstant(scope, leftTypeID, rightTypeID);
    return this.resolvedType;
  }
}
